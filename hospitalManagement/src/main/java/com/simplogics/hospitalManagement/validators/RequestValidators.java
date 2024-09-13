package com.simplogics.hospitalManagement.validators;

import com.simplogics.hospitalManagement.advice.HospitalException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;

public class RequestValidators {
    public static void check(Object O) throws HospitalException {
        if(O==null){
            throw new HospitalException(ExceptionConstants.NULL_REQUEST_EXCEPTION);
        }
    }
    public static void checkParams(Object O1,Object O2) throws HospitalException {
        if(O1==null || O2==null){
            throw new HospitalException(ExceptionConstants.NULL_REQUEST_EXCEPTION);
        }
    }
}
