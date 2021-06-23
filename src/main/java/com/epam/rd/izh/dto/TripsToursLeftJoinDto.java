package com.epam.rd.izh.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

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

    public TripsToursLeftJoinDto() {}

    public static class Builder {

        private final TripsToursLeftJoinDto joinDto = new TripsToursLeftJoinDto();

        private Builder() {
        }

        public Builder tripId(Integer tripId) {
            joinDto.tripId = tripId;
            return this;
        }

        public Builder tourId(Integer tourId) {
            joinDto.tourId = tourId;
            return this;
        }

        public Builder title(String title) {
            joinDto.title = title;
            return this;
        }

        public Builder arriveDate(LocalDateTime arriveDate) {
            joinDto.arriveDate = arriveDate;
            return this;
        }

        public Builder freeSpots(Integer freeSpots) {
            joinDto.freeSpots = freeSpots;
            return this;
        }

        public Builder instructors(Map<String, String> instructors) {
            joinDto.instructors = instructors;
            return this;
        }

        public Builder description(String description) {
            joinDto.description = description;
            return this;
        }

        public Builder direction(String direction) {
            joinDto.direction = direction;
            return this;
        }

        public Builder route(Integer route) {
            joinDto.route = route;
            return this;
        }

        public Builder cost(Integer cost) {
            joinDto.cost = cost;
            return this;
        }

        public TripsToursLeftJoinDto build() {
            return joinDto;
        }
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDateTime arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Integer getFreeSpots() {
        return freeSpots;
    }

    public void setFreeSpots(Integer freeSpots) {
        this.freeSpots = freeSpots;
    }

    public Map<String, String> getInstructors() {
        return instructors;
    }

    public void setInstructors(Map<String, String> instructors) {
        this.instructors = instructors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripsToursLeftJoinDto that = (TripsToursLeftJoinDto) o;
        return Objects.equals(tripId, that.tripId) && Objects.equals(tourId, that.tourId) && Objects.equals(title, that.title) && Objects.equals(arriveDate, that.arriveDate) && Objects.equals(freeSpots, that.freeSpots) && Objects.equals(instructors, that.instructors) && Objects.equals(description, that.description) && Objects.equals(direction, that.direction) && Objects.equals(route, that.route) && Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, tourId, title, arriveDate, freeSpots, instructors, description, direction, route, cost);
    }

    @Override
    public String toString() {
        return "TripsToursLeftJoinDto{" +
                "tripId=" + tripId +
                ", tourId=" + tourId +
                ", title='" + title + '\'' +
                ", arriveDate='" + arriveDate + '\'' +
                ", freeSpots=" + freeSpots +
                ", instructors=" + instructors +
                ", description='" + description + '\'' +
                ", direction='" + direction + '\'' +
                ", route=" + route +
                ", cost=" + cost +
                '}';
    }
}
