package com.brihaspathee.zeus.web.response;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, January 2022
 * Time: 7:16 AM
 * Project: Zeus
 * Package Name: com.zeus.web.response
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalRefDataResponseList {

    private List<InternalRefDataResponse> responseList;

    @Override
    public String toString() {
        return "InternalRefDataResponseList{" +
                "internalRefDataResponseList=" + responseList +
                '}';
    }
}
