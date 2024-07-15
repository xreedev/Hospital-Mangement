package com.simplogics.hospitalManagement.advice;

public class NullRequestException extends Exception{
    public NullRequestException(String msg) {
        super(msg);
    }
}
