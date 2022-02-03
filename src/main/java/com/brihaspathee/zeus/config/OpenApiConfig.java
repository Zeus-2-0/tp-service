package com.brihaspathee.zeus.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 20, January 2022
 * Time: 10:30 AM
 * Project: Zeus
 * Package Name: com.zeus.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
@Profile({"local", "clean"})
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${zeus-tp.api.version}") String apiVersion){
        List<Server> servers = new ArrayList<>();
        servers.add(new Server()
                .url("http://localhost:8081")
                .description("Development Server"));
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Zeus Trading Partner API")
                        .version(apiVersion)
                        .contact(new Contact()
                                .name("Balaji Varadharajan")
                                .url("http://www.zeus.com")
                                .email("vbalaji215@outlook.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/License-2.0.html"))
                )
                .servers(servers);
    }
}
