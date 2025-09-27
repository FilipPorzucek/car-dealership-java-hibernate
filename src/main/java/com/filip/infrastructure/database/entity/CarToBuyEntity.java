package com.filip.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of="carToBuyId")
@ToString(of={"carToServiceId","vin","brand","model","year"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car_to_buy")
public class CarToBuyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="car_to_buy_id")
    private Integer carToServiceId;


    @Column(name="vin")
    private String vin;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="year")
    private Short year;

    @Column(name="color")
    private String color;

    @Column(name="price")
    private BigDecimal price;


    @OneToOne(fetch = FetchType.LAZY,mappedBy = "car")
    private InvoiceEntity invoice;


}
