package com.brihaspathee.zeus.config;

import com.brihaspathee.zeus.schema.validation.JSONSchemaValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class BeanConfig {

    @Bean
    public JSONSchemaValidator getJSONSchemaValidator(){
        return new JSONSchemaValidator();
    }
}
