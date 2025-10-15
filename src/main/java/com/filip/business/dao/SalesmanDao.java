package com.filip.business.dao;

import com.filip.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

public interface SalesmanDao {
     Optional<SalesmanEntity> findByPesel(String pesel);
}
