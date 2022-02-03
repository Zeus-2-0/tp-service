package com.brihaspathee.zeus.web.request;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, January 2022
 * Time: 7:11 AM
 * Project: Zeus
 * Package Name: com.zeus.web.request
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalRefDataRequestList {

    private List<InternalRefDataRequest> internalRefDataRequestList;

    @Override
    public String toString() {
        return "InternalRefDataRequestList{" +
                "internalRefDataRequestList=" + internalRefDataRequestList +
                '}';
    }
}
