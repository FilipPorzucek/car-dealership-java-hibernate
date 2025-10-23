package com.filip.business.dao;

import com.filip.infrastructure.database.entity.CarServiceRequestEntity;

import java.util.Set;

public interface CarServiceRequestDao {
    Set<CarServiceRequestEntity> findActiveServiceRequestByCarVin(String carVin);
}
