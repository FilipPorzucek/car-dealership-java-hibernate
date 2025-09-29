package com.filip.business.managment;

import com.filip.business.dao.CarDealerShipManagmentDao;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CarDealerShipManagmentService {
    private final CarDealerShipManagmentDao carDealerShipManagmentDao;
    private final FileDataPreparationService fileDataPreparationService;

    public void purge(){
    carDealerShipManagmentDao.purge();
    }

    public void init(){
    List<?> entities =fileDataPreparationService.prepareInitData();
    carDealerShipManagmentDao.saveAll(entities);
    };

}
