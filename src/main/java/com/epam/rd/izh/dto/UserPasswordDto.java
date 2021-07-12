package com.epam.rd.izh.dto;

import lombok.Data;

@Data
public class UserPasswordDto {

    private Integer id;
    private String oldPass;
    private String newPass;
}
