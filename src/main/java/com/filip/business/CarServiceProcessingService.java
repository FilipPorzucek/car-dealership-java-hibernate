package com.filip.business;

import com.filip.business.managment.FileDataPreparationService;
import com.filip.business.managment.MechanicService;
import com.filip.domain.CarServiceProcessingRequest;
import com.filip.infrastructure.database.entity.CarToServiceEntity;
import com.filip.infrastructure.database.entity.MechanicEntity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CarServiceProcessingService {

    private final FileDataPreparationService fileDataPreparationService;
    private final MechanicService mechanicService;
    private final CarService carService;

    public void process() {

       List<CarServiceProcessingRequest> toProcess= fileDataPreparationService.prepareServiceRequestToProcess();
       toProcess.forEach(this::processRequest);
    }

    private void processRequest(CarServiceProcessingRequest request) {
        MechanicEntity mechanic = mechanicService.findMechanic(request.getMechanicPesel());
        CarToServiceEntity car = carService.findCarToService(request.getCarVin()).orElseThrow();
    }
}
