package com.brihaspathee.zeus.encoders;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, February 2022
 * Time: 4:13 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.encoders
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
public class PasswordEncodingTest {

    static final String PASSWORD = "password";

    @Test
    void hashingMD5Example(){
        log.info(DigestUtils.md5DigestAsHex(PASSWORD.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void hashingBCryptExample(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        log.info(bCryptPasswordEncoder.encode(PASSWORD));
    }

//    @Test
//    void hashingSCryptExample(){
//        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
//        log.info(pbkdf2PasswordEncoder.encode(PASSWORD));
//    }
}
