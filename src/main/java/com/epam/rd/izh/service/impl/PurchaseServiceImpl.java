package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dao.PurchaseDao;
import com.epam.rd.izh.dto.PurchaseDto;
import com.epam.rd.izh.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDao purchaseDao;

    @Override
    public List<PurchaseDto> findByUserId(Integer userId) {
        return purchaseDao.findPurchasesByUserId(userId);
    }
}
