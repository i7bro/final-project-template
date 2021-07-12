package com.epam.rd.izh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.lang.reflect.ParameterizedType;

@Data
@NoArgsConstructor
public class TourValidDto {

    private Integer id;
    private String title;
    private String description;
    private String direction;
    @Pattern(regexp = "\\d+")
    private String route;
    @Pattern(regexp = "\\d+")
    private String cost;
    private String notice;
}
