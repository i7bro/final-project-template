package com.epam.rd.izh.dto;

import java.time.LocalDateTime;

public class TripDto {

    private Integer id;
    private Integer tourId;
    private Integer freeSpots;
    private String arriveDate;
    private String main;
    private String helper;

    public TripDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public Integer getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(Integer freeSpots) {
        this.freeSpots = freeSpots;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    @Override
    public String toString() {
        return "TripDto{" +
                "id=" + id +
                ", tourId=" + tourId +
                ", freeSpots=" + freeSpots +
                ", arriveDate='" + arriveDate + '\'' +
                ", main='" + main + '\'' +
                ", helper='" + helper + '\'' +
                '}';
    }
}
