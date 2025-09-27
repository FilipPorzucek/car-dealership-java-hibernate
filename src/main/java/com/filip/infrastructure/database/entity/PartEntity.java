package com.filip.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of="partId")
@ToString(of={"partId","serial_number","description","price" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "part")
public class PartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="part_id")
    private Integer partId;

    @Column(name = "serial_number",unique = true)
    private String serialNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "part")
    private Set<ServicePartEntity> serviceParts;


}
