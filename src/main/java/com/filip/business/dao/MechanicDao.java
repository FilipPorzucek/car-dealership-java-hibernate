package com.filip.business.dao;

import com.filip.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

public interface MechanicDao {
     Optional<MechanicEntity> findByPesel(String pesel);
}
