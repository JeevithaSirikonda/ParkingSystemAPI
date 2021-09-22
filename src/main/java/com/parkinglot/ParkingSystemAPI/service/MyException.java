package com.parkinglot.ParkingSystemAPI.service;

public class MyException extends Exception{
    String exceptionMessage;

    MyException(String str){
        exceptionMessage = str;
    }
    public String toString(){
        return("MyException: "+ exceptionMessage);
    }
}
