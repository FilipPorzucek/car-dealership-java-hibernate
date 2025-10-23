package com.filip.business.managment;

import com.filip.business.dao.MechanicDao;
import com.filip.business.dao.SalesmanDao;
import com.filip.infrastructure.database.entity.MechanicEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class MechanicService {

    private final MechanicDao mechanicDao;

    public MechanicEntity findMechanic(String pesel) {

            Optional<MechanicEntity> mechanic = mechanicDao.findByPesel(pesel);
            if(mechanic.isEmpty()){
                throw new RuntimeException("Could not mechanic car by pesel:[%s] ".formatted(pesel));
            }
            return mechanic.get();

    }
}
