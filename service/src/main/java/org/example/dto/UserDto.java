package org.example.dto;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String username;
    private String birthday;
    private String email;
    private String password;
    private String gender;
    private String country;
}
