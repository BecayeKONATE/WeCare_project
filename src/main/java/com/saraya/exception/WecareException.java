package com.saraya.exception;

public class WecareException extends Exception{

    private static final long serialVersionUID = 1L;

    public WecareException(){}

    public WecareException(String message){
        super(message);
    }
}
