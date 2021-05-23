package com.epam.rd.izh.entity;

import java.util.Objects;

public class Tour {

    private Integer id;
    private String title;
    private String description;
    private Integer route;
    private Integer cost;
    private String notice;

    public Tour() {}

    public static class Builder {

        Tour tour;

        public Builder() {
            tour = new Tour();
        }

        public Builder title(String title) {
            tour.title = title;
            return this;
        }

        public Builder description(String description) {
            tour.description = description;
            return this;
        }

        public Builder route(Integer route) {
            tour.route = route;
            return this;
        }

        public Builder cost(Integer cost) {
            tour.cost = cost;
            return this;
        }

        public Builder notice(String notice) {
            tour.notice = notice;
            return this;
        }

        public Tour build() {
            return tour;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getRoute() {
        return route;
    }

    public Integer getCost() {
        return cost;
    }

    public String getNotice() {
        return notice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(id, tour.id) && Objects.equals(title, tour.title) && Objects.equals(description, tour.description) && Objects.equals(route, tour.route) && Objects.equals(cost, tour.cost) && Objects.equals(notice, tour.notice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, route, cost, notice);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", route=" + route +
                ", cost=" + cost +
                ", notice='" + notice + '\'' +
                '}';
    }
}
