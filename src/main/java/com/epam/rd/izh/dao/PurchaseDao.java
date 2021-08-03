package com.epam.rd.izh.dao;

import com.epam.rd.izh.dto.PurchaseDto;

import java.util.List;

public interface PurchaseDao {

    List<PurchaseDto> findPurchasesByUserId(Integer user_id);
}
