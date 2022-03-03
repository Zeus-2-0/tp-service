package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.service.interfaces.UserService;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.TestData;
import com.brihaspathee.zeus.test.TestMethod;
import com.brihaspathee.zeus.web.request.TestUserRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, March 2022
 * Time: 3:18 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@SpringBootTest
@Slf4j
/**
 * The @WebMvcTest annotation can be used when there is no security involved
 * Since the users has to be loaded from the DB, the context have to brought up
 * so we have to use @SprintBootTest annotation
 */
// @WebMvcTest(UserResource.class)
class UserResourceTest {

    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup(TestInfo testInfo) throws IOException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        userRequestTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestUserRequest>>() {});
        this.testInfo = testInfo;
        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
        this.requests = buildTestData(testInfo.getTestMethod().get().getName());
    }

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("classpath:com/brihaspathee/zeus/web/resource/impl/UserResourceTest.json")
    Resource resourceFile;

    private TestClass<TestUserRequest> userRequestTestClass;
    private TestInfo testInfo;
    private List<TestUserRequest> requests = new ArrayList<>();


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
    void testGetUsers(RepetitionInfo repetitionInfo) throws Exception {

        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetUsers(testUserRequest);
    }

    @RepeatedTest(3)
    void testCreateUsers(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        TestUserRequest testUserRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateCreateUser(testUserRequest);
    }


    private void validateCreateUser(TestUserRequest testUserRequest) throws Exception {
        UserDto inputUser = testUserRequest.getUserDtoRequest();
        String encryptedPwd = bCryptPasswordEncoder.encode(inputUser.getPassword());
        inputUser.setPassword(encryptedPwd);
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(inputUser, headers);
        UserDto expectedUser = testUserRequest.getExpectedUserDto();
        String inputUserString = objectMapper.writeValueAsString(inputUser);
        if(!testUserRequest.isExceptionExpected()){
            doReturn(expectedUser).when(userService).saveUser(any(UserDto.class));

            MvcResult mvcResult = mockMvc.perform(post("/api/v1/tp/user")
                            .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(inputUserString))
                    .andExpect(status().isCreated())
                    .andReturn();
            String apiResponseAsString = mvcResult.getResponse().getContentAsString();
            ZeusApiResponse actualResponse = objectMapper.readValue(apiResponseAsString, new TypeReference<ZeusApiResponse<UserDto>>() {});
            UserDto savedUser = objectMapper.convertValue(actualResponse.getResponse(), UserDto.class);
            log.info("Saved User:{}", savedUser);
            assertNotNull(savedUser.getUserId());
            assertEquals(inputUser.getUsername(), savedUser.getUsername());
            assertNotNull(savedUser.getRoles());
            //userRepository.deleteById(savedUser.getUserId());
        }else{
            if(testUserRequest.isAuthException()){
                mockMvc.perform(post("/api/v1/tp/user")
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputUserString))
                        .andExpect(status().is4xxClientError());
            }
        }
    }

    private void validateGetUsers(TestUserRequest testUserRequest) throws Exception {

        UserList expectedUserList = testUserRequest.getExpectedUserList();
        UserDto loggedInUser = testUserRequest.getLoggedInUser();
        UserDto requestUserDto = testUserRequest.getUserDtoRequest();
        if(!testUserRequest.isExceptionExpected()){
            log.info("Requested User Dto:{}", requestUserDto);
            if(requestUserDto.getUserId()!=null){
                UUID userId = requestUserDto.getUserId();
                UserDto expectedUser = expectedUserList.getUsers().get(0);
                doReturn(expectedUser).when(userService).getUserById(userId);
                MvcResult mvcResult = mockMvc.perform(get("/api/v1/tp/user?userId="+userId)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseAsString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse actualResponse = objectMapper.readValue(apiResponseAsString, new TypeReference<ZeusApiResponse<UserList>>() {});
                UserList actualUserList = objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                log.info("Actual user list:" + actualUserList);
                assertEquals(expectedUserList.getUsers().size(), actualUserList.getUsers().size());
            }else if(requestUserDto.getUsername()!=null){
                String username = requestUserDto.getUsername();
                UserDto expectedUser = expectedUserList.getUsers().get(0);
                doReturn(expectedUser).when(userService).getUserByUserName(username);
                MvcResult mvcResult = mockMvc.perform(get("/api/v1/tp/user?username="+username)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseAsString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse actualResponse = objectMapper.readValue(apiResponseAsString, new TypeReference<ZeusApiResponse<UserList>>() {});
                UserList actualUserList = objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                assertEquals(expectedUserList.getUsers().size(), actualUserList.getUsers().size());
            }else{
                Set<UserDto> expectedUsers = expectedUserList.getUsers().stream().collect(Collectors.toSet());
                doReturn(expectedUsers).when(userService).getAllUsers();
                MvcResult mvcResult = mockMvc.perform(get("/api/v1/tp/user")
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
                String apiResponseAsString = mvcResult.getResponse().getContentAsString();
                ZeusApiResponse actualResponse = objectMapper.readValue(apiResponseAsString, new TypeReference<ZeusApiResponse<UserList>>() {});
                UserList actualUserList = objectMapper.convertValue(actualResponse.getResponse(), UserList.class);
                assertEquals(expectedUserList.getUsers().size(), actualUserList.getUsers().size());
            }

        }else{
            if(testUserRequest.isAuthException()){
                String username = requestUserDto.getUsername();
                mockMvc.perform(get("/api/v1/user?username="+username)
                                .with(httpBasic(loggedInUser.getUsername(), loggedInUser.getPassword()))
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().is4xxClientError());
            }

        }
    }
}