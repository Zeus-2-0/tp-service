package com.brihaspathee.zeus.web.response;

import lombok.*;

import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiExceptionList {

    private List<ApiException> exceptions;

    @Override
    public String toString() {
        return "ApiExceptionList{" +
                "exceptions=" + exceptions +
                '}';
    }
}
