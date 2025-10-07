package com.filip.business.dao;

import com.filip.infrastructure.database.entity.CarToBuyEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;


public interface CarDao {
    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);

}
