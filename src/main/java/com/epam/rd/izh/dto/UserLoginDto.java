package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDto {

    private String login;
    private String password;
}
