package com.filip.business.managment;

import com.filip.business.CarService;
import com.filip.business.CustomerService;
import com.filip.domain.CarServiceRequest;
import com.filip.infrastructure.database.entity.CarServiceRequestEntity;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.CarToServiceEntity;
import com.filip.infrastructure.database.entity.CustomerEntity;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CarServiceRequestService {

    private final FileDataPreparationService fileDataPreparationService;
    private final CarService carService;
    private final CustomerService customerService;

    public void requestService() {
        Map<Boolean,List<CarServiceRequest>> serviceRequests = fileDataPreparationService.createCarServiceRequests().stream()
                .collect(Collectors.groupingBy(element->element.getCar().shouldExistInCarToBuy()));

        serviceRequests.get(true).forEach(this::serviceRequestsForExistingCar);
        serviceRequests.get(false).forEach(this::serviceRequestsForNewCar);
    }

    private void serviceRequestsForNewCar(CarServiceRequest carServiceRequest) {


    }

    private void serviceRequestsForExistingCar(CarServiceRequest request) {
        CarToServiceEntity car = carService.findCarToService(request.getCar().getVin())
                .orElse(findInCarToBuyAndSaveInCarToService(request.getCar()));
        CustomerEntity customer = customerService.findCustomer(request.getCustomer().getEmail());

       CarServiceRequestEntity carServiceRequestEntity=buildCarServiceRequestEntity(request,car,customer);
       customer.addServiceRequest(carServiceRequestEntity);
       customerService.saveServiceRequest(customer);
    }


    private CarToServiceEntity findInCarToBuyAndSaveInCarToService(CarServiceRequest.Car car) {
        CarToBuyEntity carToBuy = carService.findCarToBuy(car.getVin());
        return carService.saveCarToService(carToBuy);

    }

    private CarServiceRequestEntity buildCarServiceRequestEntity(CarServiceRequest request, CarToServiceEntity car,

                                                                 CustomerEntity customer) {
        OffsetDateTime when=OffsetDateTime.now();
        return CarServiceRequestEntity.builder()
                .carServiceRequestNumber(generateCarServiceRequestNumber(when))
                .receivedDateTime(when)
                .customerComment(request.getCustomerComment())
                .customer(customer)
                .car(car)
                .build();


    }

    private String generateCarServiceRequestNumber(OffsetDateTime when) {
        return "%s%s%s%s%s%s%s".formatted(
                when.getYear(),
                when.getMonth().ordinal(),
                when.getDayOfMonth(),
                when.getHour(),
                when.getMinute(),
                when.getSecond(),
                randomInt(0,100)
        );
    }

    private Object randomInt(int min, int max) {
        return new Random().nextInt(max-min)+max;
    }

}
