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

    public static final String FILE_CANNOT_BE_READ_OR_WRITTEN = "FILE CANNOT BE READ OR WRITTEN";
    public static final String DATE_FORMAT_NOT_CORRECT = "Date format not correct";
    public static final String NO_RECORDS_FOUNDS_OF_THIS_PATIENT = "NO RECORDS FOUNDS OF THIS PATIENT";
}
