package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.TourDao;
import com.epam.rd.izh.dto.TourValidDto;
import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.entity.Trip;
import com.epam.rd.izh.service.TourService;
import com.epam.rd.izh.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TourServiceImpl implements TourService {

    private final TourDao tourDao;
    private final TripService tripService;

    @Autowired
    public TourServiceImpl(TourDao tourDao, TripService tripService) {
        this.tourDao = tourDao;
        this.tripService = tripService;
    }

    @Override
    public List<Tour> getAllTours() {
        return tourDao.findAll();
    }

    @Override
    public void updateTour(Tour tour) {
        tourDao.updateTour(tour);
    }

    @Override
    public List<List<Object[]>> getAllTourGroups4() {
        return getGroups4(tourDao.findAll(), tripService.findAll());
    }

    @Override
    public List<List<Object[]>> getAllTourGroups4(String direction) {
        return getGroups4(tourDao.findAllByDirection(direction), tripService.findAll());
    }

    private List<List<Object[]>> getGroups4(List<Tour> tours, List<Trip> trips) {
        List<List<Object[]>> resultList = new ArrayList<>();
        Object[] tmpToursTrip;
        List<Trip> tmpTrips;
        List<Object[]> tmp = new ArrayList<>();

        for (Tour tour : tours) {
            tmpTrips = new ArrayList<>();
            tmpToursTrip = new Object[2];
            trips.stream()
                    .filter(trip -> trip.getTourId().equals(tour.getId()))
                    .forEach(tmpTrips::add);

            tmpToursTrip[0] = tour;
            tmpToursTrip[1] = tmpTrips;
            tmp.add(tmpToursTrip);

            if (tmp.size() == 4) {
                resultList.add(tmp);
                tmp = new ArrayList<>();
            }
        }
        resultList.add(tmp);

        return resultList;
    }

    @Override
    public Tour toTour(TourValidDto tourValidDto) {
        return Tour.builder()
                .id(tourValidDto.getId())
                .title(tourValidDto.getTitle())
                .description(tourValidDto.getDescription())
                .direction(tourValidDto.getDirection())
                .route(Integer.parseInt(tourValidDto.getRoute()))
                .cost(Integer.parseInt(tourValidDto.getCost()))
                .notice(tourValidDto.getNotice())
                .build();
    }

    @Override
    public void delete(Integer id) {
        tourDao.delete(id);
    }

    @Override
    public void save(Tour tour) {
        tourDao.save(tour);
    }

    @Override
    public Tour findTourByTitle(TourValidDto tourValidDto) {
        return tourDao.findByTitle(tourValidDto.getTitle());
    }

    @Override
    public Tour findTourById(Integer id) {
        return tourDao.findToutById(id).orElseThrow(() -> new NoSuchElementException("Tour not found"));
    }
}
