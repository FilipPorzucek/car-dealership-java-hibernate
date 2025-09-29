package com.filip.business.dao;

import java.util.List;

public interface CarDealerShipManagmentDao {
    void purge();
    void saveAll(List<?> entites);
}
