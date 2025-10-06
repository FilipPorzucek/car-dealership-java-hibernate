package com.filip.business.managment;

import com.filip.infrastructure.database.entity.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileDataPreparationService {




    public List<?> prepareInitData(){
         List<SalesmanEntity> salesman = InputDataCache
                .getInputData(Keys.InputDataGroup.INIT, Keys.Entity.SALESMAN, InputDataMapper::mapSalesman);

        List<MechanicEntity> mechanics = InputDataCache
                .getInputData(Keys.InputDataGroup.INIT, Keys.Entity.MECHANIC, InputDataMapper::mapMechanic);

        List<CarToBuyEntity> cars = InputDataCache
                .getInputData(Keys.InputDataGroup.INIT, Keys.Entity.CAR, InputDataMapper::mapCar);

        List<ServiceEntity> services = InputDataCache
                .getInputData(Keys.InputDataGroup.INIT, Keys.Entity.SERVICE, InputDataMapper::mapService);

        List<PartEntity> parts = InputDataCache
                .getInputData(Keys.InputDataGroup.INIT, Keys.Entity.PART, InputDataMapper::mapPart);

        return Stream.of(salesman,mechanics,cars,services,parts)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<Map<String,List<String>>> prepapreFirstTimePurchaseData(){
    return  InputDataCache.getInputData(Keys.InputDataGroup.BUY_FIRST_TIME, this::prepareMap);
    }
    public List<Map<String,List<String>>> prepapreNextTimePurchaseData(){
       return InputDataCache.getInputData(Keys.InputDataGroup.BUY_AGAIN, this::prepareMap);
    }

    private Map<String,List<String>> prepareMap(String line) {
        List<String> grouped = Arrays.stream(line.split("->")).map(String::trim).toList();
      return   IntStream.iterate(0, previous -> previous + 2)
                .boxed()
                .limit(3)
                .collect(Collectors.toMap(grouped::get, i -> List.of(grouped.get(i + 1).split(";"))));

    }
}
