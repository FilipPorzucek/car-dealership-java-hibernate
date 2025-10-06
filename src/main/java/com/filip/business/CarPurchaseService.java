package com.filip.business;

import com.filip.business.managment.FileDataPreparationService;
import com.filip.business.managment.Keys;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.CustomerEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CarPurchaseService {

    private final FileDataPreparationService fileDataPreparationService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SalesmanService salesmanService;

    public void purchase(){
      var firstTimeData = fileDataPreparationService.prepapreFirstTimePurchaseData();
      var nextTimeData = fileDataPreparationService.prepapreNextTimePurchaseData();

        List<CustomerEntity> firstTimeCustomers = firstTimeData.stream()
                .map(this::createFirstTimeBuyCustomer)
                .toList();
        firstTimeCustomers.forEach(customerService::issueInvoice);

    List<CustomerEntity> nextTimeCustomers = nextTimeData.stream()
            .map(this::createNextTimeBuyCustomer)
            .toList();
        nextTimeCustomers.forEach(customerService::issueInvoice);
}

    private Object createFirstTimeBuyCustomer(Map<String, List<String>> inputData) {
       CarToBuyEntity car= carService.findCarToBuy(inputData.get(Keys.Entity.CAR.toString()).get(0));
    }

private Object createNextTimeBuyCustomer(Map<String, List<String>> inputData) {
    return null;
}

}
