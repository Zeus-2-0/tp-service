package com.zeus.web.response;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, January 2022
 * Time: 9:25 AM
 * Project: Zeus
 * Package Name: com.zeus.web.response
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {

    private String exceptionCode;

    private String exceptionMessage;

    @Override
    public String toString() {
        return "ApiException{" +
                "exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
