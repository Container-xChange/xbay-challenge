package com.xbaychallenge.user.repository;

public class UserEntityBuilder {

    private String firstname;
    private String surname;
    private String email;

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public UserEntityBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserEntityBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserEntityBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserEntity build() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(firstname);
        userEntity.setSurname(surname);
        userEntity.setEmail(email);
        return userEntity;
    }

}
