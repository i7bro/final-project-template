package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.TripDao;
import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.dao.UserRequestDao;
import com.epam.rd.izh.entity.Trip;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserRequestService {

    UserRequestDao userRequestDao;
    TripDao tripDao;
    UserDao userDao;

    @Autowired
    public UserRequestService(UserRequestDao userRequestDao, TripDao tripDao, UserDao userDao) {
        this.userRequestDao = userRequestDao;
        this.tripDao = tripDao;
        this.userDao = userDao;
    }

    public void save(UserRequest userRequest) {
        userRequestDao.save(userRequest.getUser().getId(), userRequest.getTrip().getId());
    }

    public void save(String[] trips_id, String login) {
        User user = userDao.findByLogin(login)
                .orElseThrow(() -> new NoSuchElementException("No user id: " + login));
        for (String id : trips_id) {
            userRequestDao.save(user.getId(), Integer.valueOf(id));
        }
    }
}
