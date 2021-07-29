package com.epam.rd.izh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tours")
public class Tour {

    @Id
    private Integer id;
    private String title;
    private String description;
    private String direction;
    private Integer route;
    private Integer cost;
    private String notice;
}
