package com.filip.business;

import com.filip.business.dao.CarDao;
import com.filip.domain.CarServiceRequest;
import com.filip.infrastructure.database.entity.CarServiceRequestEntity;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.CarToServiceEntity;
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

    public Optional<CarToServiceEntity> findCarToService(String vin) {
        return carDao.findCarToServiceByVin(vin);
    }

    public CarToServiceEntity saveCarToService(CarToBuyEntity carToBuy) {
        CarToServiceEntity entity = CarToServiceEntity.builder()
                .vin(carToBuy.getVin())
                .brand(carToBuy.getBrand())
                .model(carToBuy.getModel())
                .year(carToBuy.getYear())
                .build();

        return carDao.saveCarToService(entity);
    }

    public CarToServiceEntity saveCarToService(CarServiceRequest.Car car) {
        CarToServiceEntity entity = CarToServiceEntity.builder()
                .vin(car.getVin())
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .build();
        return carDao.saveCarToService(entity);
    }
}
