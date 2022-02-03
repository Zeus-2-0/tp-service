package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, January 2022
 * Time: 11:11 AM
 * Project: Zeus
 * Package Name: com.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
