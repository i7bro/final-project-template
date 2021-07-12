package com.epam.rd.izh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSettingsDto {

    private Integer id;
    private String login;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
