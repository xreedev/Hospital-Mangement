package com.simplogics.hospitalManagement.validators;

import com.simplogics.hospitalManagement.advice.NullRequestException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;

public class RequestValidators {
    public static void check(Object O) throws NullRequestException {
        if(O==null){
            throw new NullRequestException(ExceptionConstants.NULL_REQUEST_EXCEPTION);
        }
    }
    public static void checkParams(Object O1,Object O2) throws NullRequestException {
        if(O1==null || O2==null){
            throw new NullRequestException(ExceptionConstants.NULL_REQUEST_EXCEPTION);
        }
    }
}
