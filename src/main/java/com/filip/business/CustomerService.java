package com.filip.business;

import com.filip.business.dao.CustomerDao;
import com.filip.domain.CarServiceRequest;
import com.filip.infrastructure.database.entity.AddressEntity;
import com.filip.infrastructure.database.entity.CustomerEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;

    void issueInvoice(CustomerEntity customer){
        customerDao.issueInvoice(customer);
    }

    public CustomerEntity findCustomer(String email) {
        Optional<CustomerEntity> customer = customerDao.findByEmail(email);
        if(customer.isEmpty()){
            throw new RuntimeException("Could not customer car by email:[%s] ".formatted(email));
        }
        return customer.get();
    }

    public void saveServiceRequest(CustomerEntity customer) {
        customerDao.saveServiceRequest(customer);
    }

    public CustomerEntity saveCustomer(CarServiceRequest.Customer customer) {
        CustomerEntity entity=CustomerEntity.builder()
        .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(AddressEntity.builder()
                        .country(customer.getAddress().getCountry())
                        .city(customer.getAddress().getCity())
                        .postalCode(customer.getAddress().getPostalCode())
                        .address(customer.getAddress().getAddress())
                        .build())
                        .build();
        return customerDao.saveCustomer(entity);
    }
}
