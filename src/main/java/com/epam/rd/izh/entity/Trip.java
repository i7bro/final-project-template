package com.epam.rd.izh.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;


public class Trip {

    private Integer id;
    private Integer tourId;
    private Integer freeSpots;
    private LocalDateTime arriveDate;
    private Map<String, String> instructors;

    public Trip() {}

    public static class Builder {

        private final Trip trip = new Trip();

        private Builder() {};

        public Builder id(Integer id) {
            trip.id = id;
            return this;
        }

        public Builder tourId(Integer tourId) {
            trip.tourId = tourId;
            return this;
        }

        public Builder freeSpots(Integer freeSpots) {
            trip.freeSpots = freeSpots;
            return this;
        }

        public Builder arriveDate(LocalDateTime arriveDate) {
            trip.arriveDate = arriveDate;
            return this;
        }

        public Builder instructors(Map<String, String> instructors) {
            trip.instructors = instructors;
            return this;
        }

        public Trip build() {
            return trip;
        }
    }

    public static Builder getBuilder() {
        return new Builder();
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

    public LocalDateTime getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDateTime arriveDate) {
        this.arriveDate = arriveDate;
    }

    public Map<String, String> getInstructors() {
        return instructors;
    }

    public void setInstructors(Map<String, String> instructors) {
        this.instructors = instructors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id) && Objects.equals(tourId, trip.tourId) && Objects.equals(freeSpots, trip.freeSpots) && Objects.equals(arriveDate, trip.arriveDate) && Objects.equals(instructors, trip.instructors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tourId, freeSpots, arriveDate, instructors);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tourId=" + tourId +
                ", freeSpots=" + freeSpots +
                ", arriveDate=" + arriveDate +
                ", instructors=" + instructors +
                '}';
    }
}
