package com.tydic.scda.model;

public class SusSequence {
    private String name;

    private Long currentValue;

    private Short increment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    public Short getIncrement() {
        return increment;
    }

    public void setIncrement(Short increment) {
        this.increment = increment;
    }
}