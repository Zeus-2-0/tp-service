package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.TestData;
import com.brihaspathee.zeus.test.TestMethod;
import com.brihaspathee.zeus.web.request.TestUserRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, February 2022
 * Time: 3:33 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.integration
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("classpath:com/brihaspathee/zeus/integration/UserResourceIT.json")
    Resource resourceFile;

    private TestClass<TestUserRequest> userRequestTestClass;
    private TestInfo testInfo;
    private List<TestUserRequest> requests = new ArrayList<>();

    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {
        //mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
        userRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestUserRequest>>() {});
        this.testInfo = testInfo;
        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
        this.requests = buildTestData(testInfo.getTestMethod().get().getName());
        //staticRequest = this.requests;
    }

    private List<TestUserRequest> buildTestData(String methodName){
        log.info("Test Class:{}", userRequestTestClass);
        TestMethod<TestUserRequest> testMethod =
                userRequestTestClass.getTestMethods().stream()
                        .filter(userTestMethod -> userTestMethod.getTestMethodName().equals(methodName))
                        .findFirst()
                        .get();
        log.info("Test Method:{}", testMethod);
        List<TestData<TestUserRequest>> userTestData = testMethod.getTestData();
        List<TestUserRequest> requests = new ArrayList<>();
        requests.addAll(userTestData.stream().map(testData -> testData.getTestData()).collect(Collectors.toList()));
        return requests;
    }

    @RepeatedTest(5)
    void testGetUsers(RepetitionInfo repetitionInfo){

        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    private void validateGetUsers(TestUserRequest testUserRequest) {

        UserList expectedUserList = testUserRequest.getExpectedUserList();
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        if(!testUserRequest.isExceptionExpected()){
            UserDto requestUserDto = testUserRequest.getUserDtoRequest();
            log.info("Requested User Dto:{}", requestUserDto);
            if(requestUserDto.getUserId()!=null){
                UUID userId = requestUserDto.getUserId();
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/tp/user?userId="+userId, ZeusApiResponse.class);
                ZeusApiResponse actualResponse = responseEntity.getBody();
                UserList userList =
                        objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                log.info("Returned User list:{}", userList);
                assertEquals(expectedUserList.getUsers().size(), userList.getUsers().size());
            }else if(requestUserDto.getUsername()!=null){
                String username = requestUserDto.getUsername();
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/tp/user?username="+username, ZeusApiResponse.class);
                ZeusApiResponse actualResponse = responseEntity.getBody();
                UserList userList =
                        objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                log.info("Returned User list:{}", userList);
                assertEquals(expectedUserList.getUsers().size(), userList.getUsers().size());
            }else{
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/tp/user", ZeusApiResponse.class);
                ZeusApiResponse actualResponse = responseEntity.getBody();
                UserList userList =
                        objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                log.info("Returned User list:{}", userList);
                assertEquals(expectedUserList.getUsers().size(), userList.getUsers().size());
            }

        }else{
            if(testUserRequest.isAuthException()){
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(loggedInUser.getUsername(), loggedInUser.getPassword())
                        .getForEntity("/api/v1/tp/user", ZeusApiResponse.class);
                assertEquals(testUserRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }

        }
    }
}
