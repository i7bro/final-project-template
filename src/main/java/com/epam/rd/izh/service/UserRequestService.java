package com.epam.rd.izh.service;

import com.epam.rd.izh.entity.UserRequest;

public interface UserRequestService {

    void save(UserRequest userRequest);

    void save(String[] trips_id, String login);

    void delete(Integer id);
}
