package com.wikia.tibia.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateUtils {

    private static final Logger log = LoggerFactory.getLogger(TemplateUtils.class);
    public static final String REGEX_PARAMETER = "(^|\n)\\|(\\s|[a-z])";
    public static final String REGEX_PARAMETER_NEW = "\\|\\s*?([A-Za-z0-9]+)\\s*?=";

    private TemplateUtils() {}

    public static String getBetweenBalancedBrackets(String text, String start) {
        int startingCurlyBrackets = text.indexOf(start);
        assert (startingCurlyBrackets >=0) : "text: " + text + " start: " + start;
        int endingCurlyBrackets = 0;
        int openBracketsCounter = 0;
        char currentChar;

        for (int i=startingCurlyBrackets; i < text.length(); i++) {
            currentChar = text.charAt(i);
            if ('{' == currentChar) {
                openBracketsCounter++;
            }

            if ('}' == currentChar) {
                openBracketsCounter--;
            }

            if (openBracketsCounter == 0) {
                endingCurlyBrackets = i+1;
                break;
            }
        }
        return text.substring(startingCurlyBrackets, endingCurlyBrackets);
    }

    public static String removeFirstAndLastLine(String text) {
        String firstLineRemoved = text.substring(text.indexOf('\n')+1);
        return firstLineRemoved.substring(0, firstLineRemoved.lastIndexOf("}}"));
    }

    public static String removeStartAndEndOfTemplate(String text) {
        if (text.length() < 2) {
            return "";
        }
        int startOfTemplate = text.indexOf('|') + 1;
        int endOfTemplate = text.indexOf("}}");
        if (startOfTemplate >= 0 && endOfTemplate >= 0) {
            return text.substring(startOfTemplate, endOfTemplate).trim();
        }
        log.error("Could not remove start and end of template.");
        return "";
    }

    // TODO work in progress
    public static Map<String, String> splitByParameter(String infoboxTemplatePartOfArticle) {
        Map<String, String> keyValuePair = new HashMap<>();
//        List<String> splitLines = Arrays.asList().split(infoboxTemplatePartOfArticle));
        List<String> tokens = new LinkedList<>();
        Pattern pattern = Pattern.compile(REGEX_PARAMETER_NEW);
        Matcher matcher = pattern.matcher(infoboxTemplatePartOfArticle);
        while (matcher.find()) {
            if (matcher.groupCount() > 0 && matcher.group(1) != null) {
                String token = matcher.group(1);
                tokens.add(token);
            }
        }


//        for (String line : splitLines) {
//            if (line.indexOf('=') != -1) {
//                String key = line.substring(0, line.indexOf('=')).trim();
//                String value = line.substring(line.indexOf('=') + 1, line.length()).trim();
//                keyValuePair.put(key, value);
//            }
//        }
        return keyValuePair;
    }
}