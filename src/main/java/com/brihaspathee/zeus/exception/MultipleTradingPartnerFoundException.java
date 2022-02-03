package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, January 2022
 * Time: 9:23 AM
 * Project: Zeus
 * Package Name: com.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class MultipleTradingPartnerFoundException extends RuntimeException{

    public MultipleTradingPartnerFoundException(String message){
        super(message);
    }

    public MultipleTradingPartnerFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
