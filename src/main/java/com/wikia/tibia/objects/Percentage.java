package com.wikia.tibia.objects;

public class Percentage {

    private String originalValue;
    private Integer value;

    public Percentage() {
        // constructor for Jackson
    }

    public Percentage(String value) {
        this.originalValue = value;
        this.value = sanitize(value);
    }

    private Integer sanitize(String value) {

        value = value.replaceAll("\\D+","");

        if (value.length() < 1) {
            return null;
        }

        return new Integer(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }
}
