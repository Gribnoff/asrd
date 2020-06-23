package com.kropotov.asrd.entities.enums;

import lombok.Getter;

public enum Status {
    DISABLE("Отключен"),
    ACTIVE("Активный");

    @Getter
    private String name;

    Status(String name) {
        this.name = name;
    }
}
