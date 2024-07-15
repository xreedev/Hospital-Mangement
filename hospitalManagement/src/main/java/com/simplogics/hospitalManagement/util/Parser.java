package com.simplogics.hospitalManagement.util;

import com.simplogics.hospitalManagement.advice.InvalidDataFormatException;
import com.simplogics.hospitalManagement.constants.ExceptionConstants;
import com.simplogics.hospitalManagement.constants.GlobalConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Parser {

    public static Integer intParser(String value) throws InvalidDataFormatException {
        try{
            return Integer.parseInt(value);
        }
        catch(NumberFormatException E){
            throw new InvalidDataFormatException(ExceptionConstants.INCORRECT_INTEGER_TYPE);
        }
    }
    public static Date dateParser(String value) throws InvalidDataFormatException {
        try{
            SimpleDateFormat dateFormat=new SimpleDateFormat(GlobalConstants.DATE_FORMAT);
            dateFormat.setTimeZone(TimeZone.getTimeZone(GlobalConstants.UTC));
            return dateFormat.parse(value);
        }
        catch(NumberFormatException | ParseException E){
            throw new InvalidDataFormatException(ExceptionConstants.INCORRECT_DATE_FORMAT);
        }
    }
}

