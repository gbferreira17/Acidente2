package br.com.fiap.Acidente.model;

public enum Role {
    ADMIN("admin"),

    USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
