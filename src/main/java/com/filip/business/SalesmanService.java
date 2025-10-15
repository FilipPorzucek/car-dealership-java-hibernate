package com.filip.business;

import com.filip.business.dao.SalesmanDao;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class SalesmanService {

    private final SalesmanDao salesmanDao;

    public SalesmanEntity findSalesman(String pesel) {

            Optional<SalesmanEntity> salesman = salesmanDao.findByPesel(pesel);
            if(salesman.isEmpty()){
                throw new RuntimeException("Could not salesman car by pesel:[%s] ".formatted(pesel));
            }
            return salesman.get();

    }
}
