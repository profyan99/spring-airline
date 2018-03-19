package net.thumbtack.airline.model;

public enum UserRole {
    ADMIN_ROLE("ADMIN"),
    CLIENT_ROLE("CLIENT");

    private final String name;

    UserRole(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
