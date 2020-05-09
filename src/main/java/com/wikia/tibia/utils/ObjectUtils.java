package com.wikia.tibia.utils;

import com.wikia.tibia.enums.YesNo;
import com.wikia.tibia.objects.Percentage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtils {

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

    public static boolean isEmpty(BookType subject) {
        return subject == null;
    }

    public static boolean isEmpty(BuildingType subject) {
        return subject == null;
    }

    public static boolean isEmpty(List<?> subject) {
        return subject == null || subject.isEmpty();
    }

    public static boolean isEmpty(ItemClass subject) {
        return subject == null;
    }

    public static boolean isEmpty(KeyType subject) {
        return subject == null;
    }

    public static boolean isEmpty(SpellType subject) {
        return subject == null;
    }

    public static boolean isEmpty(SpellSubclass subject) {
        return subject == null;
    }
}
