package com.epam.rd.izh.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TripDto {

    private Integer id;
    private Integer tourId;
    private Integer freeSpots;
    private String arriveDate;
    private String main;
    private String helper;
}
