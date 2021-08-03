package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.TourDto;
import com.epam.rd.izh.entity.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();

    void updateTour(Tour tour);

    List<List<Object[]>> getAllTourGroups4();

    List<List<Object[]>> getAllTourGroups4(String direction);

    Tour mapToTour(TourDto tourDto);

    void delete(Integer id);

    void save(Tour tour);

    Tour findTourByTitle(String title);

    Tour findTourById(Integer id);
}
