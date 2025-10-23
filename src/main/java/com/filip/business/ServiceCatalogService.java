package com.filip.business;

import com.filip.business.dao.ServiceDao;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import com.filip.infrastructure.database.entity.ServiceEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ServiceCatalogService {
    private final ServiceDao serviceDao;

    public ServiceEntity findService(String serviceCode) {
            Optional<ServiceEntity> salesman = serviceDao.findByServiceCode(serviceCode);
            if(salesman.isEmpty()){
                throw new RuntimeException("Could not salesman car by service code:[%s] ".formatted(serviceCode));
            }
            return salesman.get();


    }
}
