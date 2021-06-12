package com.epam.rd.izh.dto;

import javax.validation.constraints.Pattern;
import java.lang.reflect.ParameterizedType;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
