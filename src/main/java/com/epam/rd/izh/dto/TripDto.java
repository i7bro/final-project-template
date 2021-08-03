package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private Integer id;
    private Integer tourId;
    private Integer freeSpots;
    private String arriveDate;
    private String main;
    private String helper;
}
