package com.epam.rd.izh.dto;

import com.epam.rd.izh.util.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseDto {

    private Integer userRequest;
    private String title;
    private String arriveDate;
    private String description;
    private String direction;
    private Integer route;
    private Integer cost;
    private State state;
}
