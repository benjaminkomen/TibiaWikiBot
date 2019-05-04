package com.wikia.tibia.utils;

import com.wikia.tibia.enums.BestiaryClass;
import com.wikia.tibia.enums.BestiaryLevel;
import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.objects.Percentage;

public class ObjectUtils {

    private ObjectUtils() {
        // don't instantiate this class, it has only static members
    }

    public static boolean isEmpty(String subject) {
        return subject == null || "".equals(subject);
    }

    public static boolean isEmpty(YesNo subject) {
        return subject == null || YesNo.EMPTY.equals(subject);
    }

    public static boolean isEmpty(Percentage subject) {
        return subject == null || Percentage.EMPTY.equals(subject);
    }

    public static boolean isEmpty(BestiaryClass subject) {
        return subject == null;
    }

    public static boolean isEmpty(BestiaryLevel subject) {
        return subject == null;
    }

    public static boolean isEmpty(Integer subject) {
        return subject == null;
    }
}
