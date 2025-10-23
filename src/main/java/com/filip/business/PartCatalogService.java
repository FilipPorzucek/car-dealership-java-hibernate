package com.filip.business;

import com.filip.business.dao.PartDao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PartCatalogService {
    private final PartDao partDao;
}
