package com.filip.integration;

import com.filip.business.CarPurchaseService;
import com.filip.business.managment.CarDealerShipManagmentService;
import com.filip.business.managment.FileDataPreparationService;
import com.filip.business.managment.InputDataCache;
import com.filip.infrastructure.configuration.HibernateUtil;
import com.filip.infrastructure.database.repository.CarDealerShipMenagmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDealerShipTest {

    private CarDealerShipManagmentService carDealerShipManagmentService;
   private CarPurchaseService carPurchaseService;
    @BeforeEach
    void beforeEach(){
        FileDataPreparationService fileDataPreparationService=new FileDataPreparationService();
        this.carDealerShipManagmentService=new CarDealerShipManagmentService(
        new CarDealerShipMenagmentRepository(),
              fileDataPreparationService
        );
        this.carPurchaseService=new CarPurchaseService(
               fileDataPreparationService
        );
    }

    @AfterAll
    static void afterAll(){HibernateUtil.closeSessionFactory();}

    @Test
    @Order(1)
    void purge(){
    log.info("RUNNING ORDER 1");
        carDealerShipManagmentService.purge();
    }

    @Test
    @Order(2)
    void init(){
        log.info("RUNNING ORDER 2");
        carDealerShipManagmentService.init();

    }

    @Test
    @Order(3)
    void purchase(){
        log.info("RUNNING ORDER 3");
        carPurchaseService.purchase();

    }

    @Test
    @Order(4)
    void makeServiceRequest(){
        log.info("RUNNING ORDER 4");
    }

    @Test
    @Order(5)
    void processServiceRequest(){
        log.info("RUNNING ORDER 5");
    }

    @Test
    @Order(6)
    void printCarHistory(){
        log.info("RUNNING ORDER 6");
    }
}
