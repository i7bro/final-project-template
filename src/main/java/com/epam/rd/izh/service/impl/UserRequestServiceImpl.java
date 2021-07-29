package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.TripDao;
import com.epam.rd.izh.dao.UserDao;
import com.epam.rd.izh.dao.UserRequestDao;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.entity.UserRequest;
import com.epam.rd.izh.service.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserRequestServiceImpl implements UserRequestService {

    private final UserRequestDao userRequestDao;
    private final UserDao userDao;

    @Override
    public void save(UserRequest userRequest) {
        userRequestDao.save(userRequest.getUser().getId(), userRequest.getTrip().getId());
    }

    @Override
    public void save(String[] trips_id, String login) {
        User user = userDao.findByLogin(login)
                .orElseThrow(() -> new NoSuchElementException("No user login: " + login));
        for (String id : trips_id) {
            userRequestDao.save(user.getId(), Integer.valueOf(id));
        }
    }

    @Override
    public void delete(Integer id) {
        userRequestDao.delete(id);
    }
}
