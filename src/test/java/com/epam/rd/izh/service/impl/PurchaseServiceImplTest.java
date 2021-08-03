package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dto.PurchaseDto;
import com.epam.rd.izh.service.PurchaseService;
import com.epam.rd.izh.service.UserRequestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseServiceImplTest {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserRequestService userRequestService;


    @BeforeEach
    void setup() {
        userRequestService.save(new String[]{"1", "2"}, "2");
    }

    @AfterEach
    void delete() {
        userRequestService.delete(1);
    }

    @Test
    void findByUserId() {
        List<PurchaseDto> byUserId = purchaseService.findByUserId(2);
        assertEquals(2, byUserId.size());
    }
}
