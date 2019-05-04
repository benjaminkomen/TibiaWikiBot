package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class Percentage {

    public static final Percentage EMPTY = Percentage.of("");
    public static final Percentage UNKNOWN = Percentage.of("100%?");

    private String originalValue;
    private Integer value;

    private Percentage(String value) {
        this.originalValue = value;
        this.value = sanitize(value);
    }

    private Percentage(Integer value) {
        this.originalValue = value + "%";
        this.value = value;
    }

    public static Percentage of(String value) {
        return new Percentage(value);
    }

    public static Percentage of(Integer value) {
        return new Percentage(value);
    }

    @JsonValue
    public String getOriginalValue() {
        return originalValue;
    }

    private Integer sanitize(String value) {

        String sanitizedValue = value.replaceAll("\\D+", "");

        if (sanitizedValue.length() < 1) {
            return null;
        }

        return Integer.valueOf(sanitizedValue);
    }

    // Equality is determined by originalValue, because the value can strip away question marks and is therefore not reliable for a correct equality check
    @Override
    public boolean equals(Object other) {
        return other instanceof Percentage &&
                Objects.equals(originalValue, ((Percentage) other).getOriginalValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalValue);
    }
}
