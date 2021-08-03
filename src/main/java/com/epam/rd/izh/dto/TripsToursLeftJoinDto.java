package com.epam.rd.izh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripsToursLeftJoinDto {

    private Integer tripId;
    private Integer tourId;
    private String title;
    private LocalDateTime arriveDate;
    private Integer freeSpots;
    private Map<String, String> instructors;
    private String description;
    private String direction;
    private Integer route;
    private Integer cost;
}
