package net.thumbtack.airline.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMIN_ROLE("ADMIN"),
    CLIENT_ROLE("CLIENT");

    private final String name;

    UserRole(String s) {
        this.name = s;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.name;
    }

}
