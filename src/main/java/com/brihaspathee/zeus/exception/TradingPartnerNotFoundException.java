package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, January 2022
 * Time: 9:21 AM
 * Project: Zeus
 * Package Name: com.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class TradingPartnerNotFoundException extends RuntimeException{

    public TradingPartnerNotFoundException(String message){
        super(message);
    }

    public TradingPartnerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
