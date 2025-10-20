package com.filip.business.dao;

import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.CarToServiceEntity;

import java.util.Optional;


public interface CarDao {
    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);
    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);
    CarToServiceEntity saveCarToService(CarToServiceEntity entity);
}
