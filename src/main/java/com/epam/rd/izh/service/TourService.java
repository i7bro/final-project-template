package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.TourDao;
import com.epam.rd.izh.dto.TourValidDto;
import com.epam.rd.izh.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class TourService {

    TourDao tourDao;

    @Autowired
    public TourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public List<Tour> getAllTours() {
        return tourDao.findAll();
    }

    public void updateTour(Tour tour) {
        tourDao.updateTour(tour);
    }

    public List<List<Tour>> getAllTourGroups4() {
        List<Tour> all = tourDao.findAll();
        List<List<Tour>> tourGroups = new ArrayList<>();
        List<Tour> tmp = new ArrayList<>();

        for (Tour tour : all) {
            tmp.add(tour);
            if (tmp.size() == 4) {
                tourGroups.add(tmp);
                tmp = new ArrayList<>();
            }
        }
        tourGroups.add(tmp);
        int[] arr = {1,2,3,4,5};
        int[] ar1 = new int[3];

        return tourGroups;
    }

    public Tour getTour(TourValidDto tourValidDto) {
        return Tour.builder()
                .id(tourValidDto.getId())
                .title(tourValidDto.getTitle())
                .description(tourValidDto.getDescription())
                .route(Integer.parseInt(tourValidDto.getRoute()))
                .cost(Integer.parseInt(tourValidDto.getCost()))
                .notice(tourValidDto.getNotice())
                .build();
    }

    public void delete(Integer id) {
        tourDao.delete(id);
    }

    public void save(Tour tour) {
        tourDao.save(tour);
    }

    public Tour findTourByTitle(TourValidDto tourValidDto) {
        return tourDao.findByTitle(tourValidDto.getTitle());
    }
}
