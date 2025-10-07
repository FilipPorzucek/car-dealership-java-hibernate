package com.filip.business;

import com.filip.business.managment.FileDataPreparationService;
import com.filip.business.managment.Keys;
import com.filip.infrastructure.database.entity.CarToBuyEntity;
import com.filip.infrastructure.database.entity.CustomerEntity;
import com.filip.infrastructure.database.entity.InvoiceEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
     SalesmanEntity salesman=salesmanService.findSalesman(inputData.get(Keys.Entity.SALESMAN.toString()).get(0));
    InvoiceEntity invoice= buildInvoice(car,salesman);


    return fileDataPreparationService.buildCustomerEntity(inputData.get(Keys.Entity.CUSTOMER.toString()),invoice);

    }

    private CustomerEntity createNextTimeBuyCustomer(Map<String, List<String>> inputData) {
       CustomerEntity existingCustomer=customerService.findCustomer(inputData.get(Keys.Entity.CUSTOMER.toString()).get(0));
        CarToBuyEntity car= carService.findCarToBuy(inputData.get(Keys.Entity.CAR.toString()).get(0));
        SalesmanEntity salesman=salesmanService.findSalesman(inputData.get(Keys.Entity.SALESMAN.toString()).get(0));
        InvoiceEntity invoice=buildInvoice(car,salesman);
        existingCustomer.getInvoices().add(invoice);
        return existingCustomer;
    }

    private InvoiceEntity buildInvoice(CarToBuyEntity car, SalesmanEntity salesman) {
        return InvoiceEntity.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .dateTime(OffsetDateTime.now())
                .car(car)
                .salesman(salesman)
                .build();
    }



}
