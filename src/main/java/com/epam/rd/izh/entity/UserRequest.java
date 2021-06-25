package com.epam.rd.izh.entity;

import com.epam.rd.izh.util.State;

public class UserRequest {

    Integer id;
    User user;
    Trip trip;
    State state;

    public UserRequest() {}

    public static class Builder {

        private final UserRequest userRequest = new UserRequest();

        private Builder() {}

        public Builder id(Integer id) {
            userRequest.setId(id);
            return this;
        }

        public Builder user(User user) {
            userRequest.setUser(user);
            return this;
        }

        public Builder trip(Trip trip) {
            userRequest.setTrip(trip);
            return this;
        }

        public Builder state(State state) {
            userRequest.setState(state);
            return this;
        }

        public UserRequest build() {
            return userRequest;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", user=" + user +
                ", trip=" + trip +
                ", state=" + state +
                '}';
    }
}
