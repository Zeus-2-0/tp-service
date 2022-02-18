package com.brihaspathee.zeus.exception;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 15, February 2022
 * Time: 2:48 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.exception
 * To change this template use File | Settings | File and Code Template
 */
public class AuthorityNotFoundException extends RuntimeException{

    public AuthorityNotFoundException(String message){
        super(message);
    }

    public AuthorityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
