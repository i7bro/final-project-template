package com.epam.rd.izh.dao;

public interface UserRequestDao {

    void save(Integer userId, Integer trip_id);

    void delete(Integer id);
}
