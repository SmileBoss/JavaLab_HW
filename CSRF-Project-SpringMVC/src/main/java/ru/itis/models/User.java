package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String email;
    private String password;
    private String confirmCode;
    private Boolean isDeleted;
}
