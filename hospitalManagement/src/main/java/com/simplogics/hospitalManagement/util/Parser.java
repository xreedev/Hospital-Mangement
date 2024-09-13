package com.simplogics.hospitalManagement.util;

import com.simplogics.hospitalManagement.advice.HospitalException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;
import com.simplogics.hospitalManagement.constants.GlobalConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Parser {

    public static Integer intParser(String value) throws HospitalException {
        try{
            return Integer.parseInt(value);
        }
        catch(NumberFormatException E){
            throw new HospitalException(ExceptionConstants.INCORRECT_INTEGER_TYPE);
        }
    }
    public static Date dateParser(String value) throws HospitalException {
        try{
            SimpleDateFormat dateFormat=new SimpleDateFormat(GlobalConstants.DATE_FORMAT);
            dateFormat.setTimeZone(TimeZone.getTimeZone(GlobalConstants.UTC));
            return dateFormat.parse(value);
        }
        catch(NumberFormatException | ParseException E){
            throw new HospitalException(ExceptionConstants.INCORRECT_DATE_FORMAT);
        }
    }
}

