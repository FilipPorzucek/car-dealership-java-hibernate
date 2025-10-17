package com.filip.business.managment;

import com.filip.domain.CarServiceRequest;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CarServiceRequestService {

    private final FileDataPreparationService fileDataPreparationService;

    public void requestService() {
        Map<Boolean,List<CarServiceRequest>> serviceRequests = fileDataPreparationService.createCarServiceRequests().stream()
                .collect(Collectors.groupingBy(element->element.getCar().shouldExistInCarToBuy()));
    }
}
