package com.filip.business;

import com.filip.business.dao.CarDao;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CarService {

    private final CarDao carDao;
    public CarToBuyEntity findCarToBuy(String vin) {
        Optional<CarToBuyEntity> carToBuyByVin = carDao.findCarToBuyByVin(vin);
        if(carToBuyByVin.isEmpty()){
            throw new RuntimeException("Could not find car by vin:[%s] ".formatted(vin));
        }
    return carToBuyByVin.get();
    }
}
