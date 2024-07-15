package com.simplogics.hospitalManagement.constants;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

public class ExceptionConstants {
    public static final String NULL_REQUEST_EXCEPTION="REQUEST BODY CANNOT BE NULL";
    public static final String PATIENT_NAME_REQUIRED="PATIENT NAME CANNOT BE NULL";
    public static final String FIELD_CANNOT_BE_NULL="NO FIELDS CAN BE NULL";
    public static final String PATIENT_DOES_NOT_EXIST="PATIENT DOES NOT EXIST";
    public static final String PROCEDURE_DOES_NOT_EXIST="PROCEDURE DOES NOT EXIST";
    public static final String INCORRECT_INTEGER_TYPE="INTEGER FORMAT IS WRONG";
    public static final String INCORRECT_DATE_FORMAT="INCORRECT DATE FORMAT,REQUIRED:";

}
