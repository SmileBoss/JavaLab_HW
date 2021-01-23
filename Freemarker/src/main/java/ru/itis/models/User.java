package ru.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    private Integer id;
    private String firstname;
    private String email;
    private String lastname;

    public User(String firstname, String email, String lastname) {
        this.firstname = firstname;
        this.email = email;
        this.lastname = lastname;
    }

    public User(Integer id, String firstname, String email, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.email = email;
        this.lastname = lastname;
    }

    public User() {
    }
}
