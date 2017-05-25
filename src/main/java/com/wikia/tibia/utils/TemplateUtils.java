package com.wikia.tibia.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateUtils {

    private static final Logger log = LoggerFactory.getLogger(TemplateUtils.class);

    private TemplateUtils() {}

    public static String getBetweenBalancedBrackets(String text, String start) {
        int startingCurlyBrackets = text.indexOf(start);
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
        int startOfTemplate = text.indexOf('|') + 1;
        int endOfTemplate = text.indexOf("}}");
        if (startOfTemplate >= 0 && endOfTemplate >= 0) {
            return text.substring(startOfTemplate, endOfTemplate).trim();
        }
        log.error("Could not remove start and end of template.");
        return text;
    }
}