package com.filip.domain;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class CarServiceRequest {

    Customer customer;
    Car car;
    String customerComment;


    @With
    @Value
    @Builder
    public static class Customer {
        private String name;
        private String surname;
        private String phone;
        private String email;
        Address address;

    }

    @With
    @Value
    @Builder
    public static class Address {
        private Integer addressId;
        private String country;
        private String city;
        private String postalCode;
        private String address;


    }

    @With
    @Value
    @Builder
    public static class Car {
        private String vin;
        private String brand;
        private String model;
        private Short year;
    }

}
