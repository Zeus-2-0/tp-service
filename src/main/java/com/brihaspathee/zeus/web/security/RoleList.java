package com.brihaspathee.zeus.web.security;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, February 2022
 * Time: 5:14 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.security
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleList {

    private List<RoleDto> roleDtoList;

    @Override
    public String toString() {
        return "RoleList{" +
                "roleDtoList=" + roleDtoList +
                '}';
    }
}
