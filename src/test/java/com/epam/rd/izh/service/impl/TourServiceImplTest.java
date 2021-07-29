package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dto.TourDto;
import com.epam.rd.izh.entity.Tour;
import com.epam.rd.izh.service.TourService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourServiceImplTest {

    private final TourService tourService;

    @Autowired
    public TourServiceImplTest(TourService tourService) {
        this.tourService = tourService;
        Tour tour = new Tour(4, "title", "description", "direction", 10, 10, "none");
    }

    @Test
    void getAllTours() {
        List<Tour> allTours = tourService.getAllTours();
        assertEquals(3, allTours.size());
    }

    @Test
    void updateTour() {
        Tour tourById = tourService.findTourById(3);
        tourById.setDescription("test");
        tourService.updateTour(tourById);
        Tour after = tourService.findTourById(3);
        assertEquals("test", after.getDescription());
    }

    @Test
    void getAllTourGroups4() {
        List<List<Object[]>> allTourGroups4 = tourService.getAllTourGroups4();
        assertEquals(2, allTourGroups4.get(0).get(0).length);
    }

    @Test
    void testGetAllTourGroups4_by_direction() {
        List<List<Object[]>> byDirection = tourService.getAllTourGroups4("kavkaz");
        assertEquals(2, byDirection.get(0).get(0).length);
    }

    @Test
    void map_toTour() {
        Tour tour = tourService.mapToTour(
                new TourDto(5, "title", "description", "direction", 10, 10, "none"));
        assertNotNull(tour);
    }

    @Test
    @Transactional
    void delete() {
        tourService.save(new Tour(5, "title1", "description", "direction", 10, 10, "none"));
        int size = tourService.getAllTours().size();
        tourService.delete(4);
        assertEquals(1, size - tourService.getAllTours().size());
    }


    @Test
    void findTourByTitle() {
        assertEquals(1, tourService.findTourByTitle("Around Elbrus").getId());
    }

    @Test
    void findTourById() {
        assertEquals("Around Elbrus", tourService.findTourById(1).getTitle());
    }
}
