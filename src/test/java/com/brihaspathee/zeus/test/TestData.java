package com.brihaspathee.zeus.test;

import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 26, January 2022
 * Time: 4:26 PM
 * Project: Zeus
 * Package Name: com.zeus.test
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestData<E> {

    private E testData;

    @Override
    public String toString() {
        return "TestData{" +
                "testData=" + testData +
                '}';
    }
}
