package com.filip.business.dao;

import com.filip.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerDao {

    Optional<CustomerEntity> findByEmail(String email);

    void issueInvoice(CustomerEntity customer);
}
