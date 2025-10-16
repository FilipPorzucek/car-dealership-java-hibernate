package com.filip.business.managment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarServiceRequestService {

    private final FileDataPreparationService fileDataPreparationService;

    public void requestService() {
        fileDataPreparationService.createCarServiceRequests();
    }
}
