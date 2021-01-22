package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.javalab.models.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String email;
    private String nickname;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}

