package com.epam.rd.izh.dao;

import com.epam.rd.izh.entity.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDao {

    List<Tour> findAll();

    void updateTour(Tour tour);

    void delete(Integer id);

    void save(Tour tour);

    Optional<Tour> findByTitle(String title);

    List<Tour> findAllByDirection(String direction);

    Optional<Tour> findToutById(Integer id);
}
