package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsDto {

    private Integer id;
    private String login;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
}
