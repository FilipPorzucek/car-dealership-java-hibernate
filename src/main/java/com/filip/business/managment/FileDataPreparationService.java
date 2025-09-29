package com.filip.business.managment;

import java.util.List;

public class FileDataPreparationService {


    public List<?> prepareInitData(){
        InputDataCash
                .getInputData(Keys.InputDataGroup.INIT,Keys.Entity.SALESMAN,);
    }
}
