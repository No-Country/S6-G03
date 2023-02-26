package com.nocountry.list;

public enum ECategory {

    CARPENTRY("CARPINTERÍA"),
    PLUMBING("PLOMERÍA");

    private final String stringCategory;

    ECategory(String stringCategory) {
        this.stringCategory = stringCategory;
    }

    @Override
    public String toString() {
        return stringCategory;
    }

    public static ECategory typeOrValue(String value) {
        for (ECategory type : ECategory.class.getEnumConstants()) {
            if (type.toString().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("CATEGORY NOT FOUND");
    }
}