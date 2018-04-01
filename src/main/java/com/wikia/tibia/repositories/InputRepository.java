package com.wikia.tibia.repositories;

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
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputRepository {

    private static final Logger log = LoggerFactory.getLogger(InputRepository.class);

    public String getInput(String fileName) {
        try {
            return getFileContents(fileName);
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

    private String getFileContents(String fileName) throws NoSuchFileException {
        final StringBuilder result = new StringBuilder("");
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource(fileName);
        final File inputFile = resource != null ? new File(resource.getFile()) : null;

        if (inputFile == null || !inputFile.exists()) {
            throw new NoSuchFileException("There is no file with the provided name.");
        }

        try (Scanner scanner = new Scanner(inputFile)) {

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

    public static class InputFileNotFoundException extends RuntimeException {

        public InputFileNotFoundException() {
            super();
        }

        public InputFileNotFoundException(String error) {
            super(error);
        }
    }
}
