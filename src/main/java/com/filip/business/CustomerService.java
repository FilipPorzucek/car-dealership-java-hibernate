package com.filip.business;

import com.filip.business.dao.CustomerDao;
import com.filip.infrastructure.database.entity.CustomerEntity;
import com.filip.infrastructure.database.entity.SalesmanEntity;
import lombok.AllArgsConstructor;

import java.util.Optional;

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
}
