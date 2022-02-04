package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.TestData;
import com.brihaspathee.zeus.test.TestMethod;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.request.TestTradingPartnerRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, February 2022
 * Time: 3:02 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TradingPartnerResourceIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("classpath:com/brihaspathee/zeus/integration/TradingPartnerResourceIT.json")
    Resource resourceFile;

    private TestClass<TestTradingPartnerRequest> tradingPartnerTestClass;

    @BeforeEach
    void setUp() throws IOException {
        tradingPartnerTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestTradingPartnerRequest>>() {});
    }

    @Test
    void testGetAllTradingPartners(){
        List<TestTradingPartnerRequest> requests = buildTestData("testGetAllTradingPartners");
        log.info("Requests:{}", requests);
        requests.stream().forEach(
                testTradingPartnerRequest -> {
                    TradingPartnerList expectedTradingPartnerList = testTradingPartnerRequest.getTradingPartnerListResponse();
                    UserDto userDto = testTradingPartnerRequest.getUserDto();
                    if(!testTradingPartnerRequest.isExceptionExpected()){
                        ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                                .withBasicAuth(userDto.getUsername(), userDto.getPassword())
                                .getForEntity("/api/v1/tp", ZeusApiResponse.class);
                        ZeusApiResponse actualResponse = responseEntity.getBody();
                        TradingPartnerList tradingPartnerList =
                                objectMapper.convertValue(actualResponse.getResponse(), TradingPartnerList.class);
                        log.info("Trading Partner List Size: {}", tradingPartnerList.getTradingPartnerDtos().size() );
                        assertEquals(expectedTradingPartnerList.getTradingPartnerDtos().size(), tradingPartnerList.getTradingPartnerDtos().size());
                    }
                }
        );
    }

    private List<TestTradingPartnerRequest> buildTestData(String methodName){
        log.info("Test Class:{}", tradingPartnerTestClass);
        TestMethod<TestTradingPartnerRequest> testMethod =
                tradingPartnerTestClass.getTestMethods().stream()
                        .filter(tradingPartnerTestMethod -> tradingPartnerTestMethod.getTestMethodName().equals(methodName))
                        .findFirst()
                        .get();
        log.info("Test Method:{}", testMethod);
        List<TestData<TestTradingPartnerRequest>> tradingPartnerTestData = testMethod.getTestData();
        List<TestTradingPartnerRequest> requests = new ArrayList<>();
        requests.addAll(tradingPartnerTestData.stream().map(testData -> testData.getTestData()).collect(Collectors.toList()));
        return requests;
    }

}
