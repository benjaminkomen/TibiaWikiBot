package com.wikia.tibia.repositories

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors

object InputRepository {

    private const val DEFAULT_CHARSET = "UTF-8"
    private val logger = LoggerFactory.getLogger(InputRepository::class.java)

    fun getInput(fileName: String): String {
        return getInput(fileName, DEFAULT_CHARSET)
    }

    private fun getInput(fileName: String, charsetName: String): String {
        return try {
            getFileContents(fileName, charsetName)
        } catch (e: NoSuchFileException) {
            throw InputFileNotFoundException("The requested file $fileName could not be located.")
        }
    }

    fun getFolderContents(folderName: String?): List<Path> {
        val classLoader = javaClass.classLoader
        var resource = URI.create("")
        try {
            resource = classLoader.getResource(folderName).toURI()
        } catch (e: URISyntaxException) {
            logger.error("URI {} not well formed.", resource, e)
        }
        try {
            Files.walk(Paths.get(resource)).use { paths ->
                return paths
                        .filter { Files.isRegularFile(it) }
                        .collect(Collectors.toList())
            }
        } catch (e: IOException) {
            logger.error("An error occurred when reading the folder contents of folderName {}. Make sure it actually exists.", folderName, e)
        }
        return emptyList()
    }

    @Throws(NoSuchFileException::class)
    private fun getFileContents(fileName: String?, charsetName: String): String {
        val result = StringBuilder()
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource(fileName)
        val inputFile = if (resource != null) File(resource.file) else null
        if (inputFile == null || !inputFile.exists()) {
            throw NoSuchFileException("There is no file with the provided name.")
        }
        try {
            Scanner(inputFile, charsetName).use { scanner ->
                while (scanner.hasNextLine()) {
                    if ("" != result.toString()) {
                        result.append("\n")
                    }
                    val line = scanner.nextLine()
                    result.append(line)
                }
            }
        } catch (e: IOException) {
            logger.error("An error occurred when reading the file {}.", fileName, e)
        }
        return result.toString()
    }

    fun <T> getCSVFile(fileName: String, clazz: Class<T>): List<T> {
        return getCSVFile(fileName, DEFAULT_CHARSET, clazz)
    }

    private fun <T> getCSVFile(fileName: String, charsetName: String, clazz: Class<T>?): List<T> {
        val result: MutableList<T> = ArrayList()
        val mapper = CsvMapper()
        val schema = mapper
                .typedSchemaFor(clazz)
                .withHeader()
        val reader = CsvMapper().readerFor(clazz).with(schema)
        val fileToRead = getInput(fileName, charsetName)
        try {
            val iterator: MappingIterator<T> = reader.readValues(fileToRead)
            while (iterator.hasNext()) {
                result.add(iterator.next())
            }
        } catch (e: IOException) {
            logger.error("Unable to deserialize CSV to Java", e)
        }
        return result
    }

    class InputFileNotFoundException(error: String) : RuntimeException(error)
}