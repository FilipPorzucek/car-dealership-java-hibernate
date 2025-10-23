package com.filip.business;

import com.filip.business.dao.CarServiceProcessingDao;
import com.filip.business.managment.CarServiceRequestService;
import com.filip.business.managment.FileDataPreparationService;
import com.filip.business.managment.MechanicService;
import com.filip.domain.CarServiceProcessingRequest;
import com.filip.infrastructure.database.entity.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CarServiceProcessingService {

    private final FileDataPreparationService fileDataPreparationService;
    private final MechanicService mechanicService;
    private final CarService carService;
    private final CarServiceRequestService carServiceRequestService;
    private final ServiceCatalogService serviceCatalogService;
    private final PartCatalogService partCatalogService;
    private final CarServiceProcessingDao carServiceProcessingDao;

    public void process() {

       List<CarServiceProcessingRequest> toProcess= fileDataPreparationService.prepareServiceRequestToProcess();
       toProcess.forEach(this::processRequest);
    }

    private void processRequest(CarServiceProcessingRequest request) {
        MechanicEntity mechanic = mechanicService.findMechanic(request.getMechanicPesel());
        CarServiceRequestEntity serviceRequest=carServiceRequestService.findAnyActiveServiceRequest(request.getCarVin());

        ServiceEntity service = serviceCatalogService.findService(request.getServiceCode());

        ServiceMechanicEntity serviceMechanicEntity=buildServiceMechanicEntity(request, mechanic, serviceRequest,service);

        if (Objects.isNull(request.getPartSerialNumber()) || Objects.isNull(request.getPartQuantity())) {
        carServiceProcessingDao.process(serviceRequest,serviceMechanicEntity);

        }else {

            PartEntity part=partCatalogService.findPart(request.getPartSerialNumber());
            ServicePartEntity servicePartEntity=buildServiceEntity(request,serviceRequest,part);
            carServiceProcessingDao.process(serviceRequest,serviceMechanicEntity,servicePartEntity);
        }

    }
}
