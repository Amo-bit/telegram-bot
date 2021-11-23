package com.github.amobit.parser.util;

public enum Genre {
    BUSINESS("Бизнес-книги"),
    CHILDREN("Детские-книги"),
    CLASSIC("Классическая-литература"),
    DETECTIVE("Детективы"),
    FANTASTIC("Фантастика"),
    FANTASY("Фэнтези"),
    FOREIGN("Зарубежная-литература"),
    RUSSIAN("Русская-литература"),
    SCIENTIFIC("Наука-и-образование");

    private final String genreName;

    Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }
}
