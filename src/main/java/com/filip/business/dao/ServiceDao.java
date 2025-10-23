package com.filip.business.dao;

import com.filip.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

public interface ServiceDao {
    Optional<ServiceEntity> findByServiceCode(String serviceCode);
}
