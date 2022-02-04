package com.brihaspathee.zeus.test;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 26, January 2022
 * Time: 4:25 PM
 * Project: Zeus
 * Package Name: com.zeus.test
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestClass<E> {

    private String testClassName;

    private List<TestMethod<E>> testMethods;

    @Override
    public String toString() {
        return "TestClass{" +
                "testClassName='" + testClassName + '\'' +
                ", testMethods=" + testMethods +
                '}';
    }
}
