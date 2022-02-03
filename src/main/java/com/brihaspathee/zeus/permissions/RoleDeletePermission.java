package com.brihaspathee.zeus.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, December 2021
 * Time: 4:03 PM
 * Project: spring-security
 * Package Name: com.example.springsecurity.permissions.security
 * To change this template use File | Settings | File and Code Template
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('role.delete')")
public @interface RoleDeletePermission {
}
