package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 15, February 2022
 * Time: 2:44 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String message){
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
