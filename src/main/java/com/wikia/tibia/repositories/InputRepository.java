package com.wikia.tibia.repositories;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputRepository {

    private static final Logger log = LoggerFactory.getLogger(InputRepository.class);
    private static final String DEFAULT_CHARSET = "UTF-8";

    public String getInput(String fileName) {
        return getInput(fileName, DEFAULT_CHARSET);
    }

    public String getInput(String fileName, String charsetName) {
        try {
            return getFileContents(fileName, charsetName);
        } catch (NoSuchFileException e) {
            throw new InputFileNotFoundException(String.format("The requested file %s could not be located.", fileName));
        }
    }

    public List<Path> getFolderContents(String folderName) {
        final ClassLoader classLoader = getClass().getClassLoader();
        URI resource = URI.create("");

        try {
            resource = classLoader.getResource(folderName).toURI();
        } catch (URISyntaxException e) {
            log.error("URI {} not well formed.", resource, e);
        }

        try (Stream<Path> paths = Files.walk(Paths.get(resource))) {
            return paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("An error occured when reading the folder contents of folderName {}. Make sure it actually exists.", folderName, e);
        }

        return Collections.emptyList();
    }

    private String getFileContents(String fileName, String charsetName) throws NoSuchFileException {
        final StringBuilder result = new StringBuilder();
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource(fileName);
        final File inputFile = resource != null ? new File(resource.getFile()) : null;

        if (inputFile == null || !inputFile.exists()) {
            throw new NoSuchFileException("There is no file with the provided name.");
        }

        try (Scanner scanner = new Scanner(inputFile, charsetName)) {

            while (scanner.hasNextLine()) {

                if (!"".equals(result.toString())) {
                    result.append("\n");
                }

                String line = scanner.nextLine();
                result.append(line);
            }

        } catch (IOException e) {
            log.error("An error occured when reading the file {}.", fileName, e);
        }
        return result.toString();
    }

    public <T> List<T> getCSVFile(String fileName, Class<T> clazz) {
        return getCSVFile(fileName, DEFAULT_CHARSET, clazz);
    }

    public <T> List<T> getCSVFile(String fileName, String charsetName, Class<T> clazz) {

        List<T> result = new ArrayList<>();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper
                .typedSchemaFor(clazz)
                .withHeader();

        ObjectReader reader = new CsvMapper().readerFor(clazz).with(schema);

        final String fileToRead = getInput(fileName, charsetName);

        try {
            MappingIterator<T> iterator = reader.readValues(fileToRead);
            while (iterator.hasNext()) {
                result.add(iterator.next());
            }
        } catch (IOException e) {
            log.error("Unable to deserialize CSV to Java", e);
        }
        return result;
    }

    public static class InputFileNotFoundException extends RuntimeException {

        public InputFileNotFoundException() {
            super();
        }

        public InputFileNotFoundException(String error) {
            super(error);
        }
    }
}
