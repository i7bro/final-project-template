package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.lang.reflect.ParameterizedType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourDto {

    private Integer id;
    private String title;
    private String description;
    private String direction;
    private Integer route;
    private Integer cost;
    private String notice;
}
