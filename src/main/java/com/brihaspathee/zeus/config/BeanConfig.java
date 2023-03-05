package com.brihaspathee.zeus.config;

import com.brihaspathee.zeus.auth.TPUserDetailsService;
import com.brihaspathee.zeus.schema.validation.JSONSchemaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, February 2022
 * Time: 2:23 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final TPUserDetailsService tpUserDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public JSONSchemaValidator getJSONSchemaValidator(){
        return new JSONSchemaValidator();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(tpUserDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;

    }
}
