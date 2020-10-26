package ru.itis.javalab.models;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
