package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDto {

    private Integer id;
    private String oldPass;
    private String newPass;
}
