package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.PurchaseDto;

import java.util.List;

public interface PurchaseService {

    List<PurchaseDto> findByUserId(Integer userId);
}
