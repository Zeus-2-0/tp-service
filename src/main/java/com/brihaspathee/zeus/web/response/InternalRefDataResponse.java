package com.brihaspathee.zeus.web.response;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 26, January 2022
 * Time: 11:25 AM
 * Project: Zeus
 * Package Name: com.zeus.web.response
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalRefDataResponse {

    private String internalListCode;

    private String internalListTypeName;

    private boolean valid;

    @Override
    public String toString() {
        return "InternalRefDataResponse{" +
                "internalListCode='" + internalListCode + '\'' +
                ", internalListTypeName='" + internalListTypeName + '\'' +
                ", valid=" + valid +
                '}';
    }
}
