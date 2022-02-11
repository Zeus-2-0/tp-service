package com.brihaspathee.zeus.integration;

import com.brihaspathee.zeus.domain.repository.TradingPartnerRepository;
import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.test.TestClass;
import com.brihaspathee.zeus.test.TestData;
import com.brihaspathee.zeus.test.TestMethod;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.request.TestTradingPartnerRequest;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    WebApplicationContext wac;

    @Autowired
    TradingPartnerRepository tradingPartnerRepository;

    protected MockMvc mockMvc;

    @MockBean
    ReferenceDataService referenceDataService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("classpath:com/brihaspathee/zeus/integration/TradingPartnerResourceIT.json")
    Resource resourceFile;

    private TestClass<TestTradingPartnerRequest> tradingPartnerTestClass;
    private TestInfo testInfo;
    private List<TestTradingPartnerRequest> requests = new ArrayList<>();
    private static List<TestTradingPartnerRequest> staticRequest = new ArrayList<>();

    @BeforeEach
    void setUp(TestInfo testInfo) throws IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
        tradingPartnerTestClass = objectMapper.readValue(resourceFile.getFile(), new TypeReference<TestClass<TestTradingPartnerRequest>>() {});
        this.testInfo = testInfo;
        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
        this.requests = buildTestData(testInfo.getTestMethod().get().getName());
        staticRequest = this.requests;
    }

//    @BeforeEach
//    void init(TestInfo testInfo){
//        this.testInfo = testInfo;
//        log.info("Test Method name:{}", testInfo.getTestMethod().get().getName());
//    }

    //@ParameterizedTest
    //@MethodSource("generator")
    //@Test
    @RepeatedTest(3)
    void testGetAllTradingPartners(RepetitionInfo repetitionInfo){
//        List<TestTradingPartnerRequest> requests = buildTestData(testInfo.getTestMethod().get().getName());
//        log.info("Requests:{}", requests);
//        requests.stream().forEach(
//                testTradingPartnerRequest -> {
//                    validateGetAllTradingPartners(testTradingPartnerRequest);
//                }
//        );
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        TestTradingPartnerRequest testTradingPartnerRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetAllTradingPartners(testTradingPartnerRequest);
    }

    @RepeatedTest(5)
    void testCreateTradingPartner(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        //doNothing().when(referenceDataService).lookupReferenceData(any());
        doReturn(null).when(referenceDataService).lookupReferenceData(any());
        TestTradingPartnerRequest testTradingPartnerRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        //log.info("Test Trading Partner Request:{}", testTradingPartnerRequest);
        validateCreateTradingPartner(testTradingPartnerRequest);
    }

    @RepeatedTest(4)
    void testGetTradingPartnerById(RepetitionInfo repetitionInfo) throws Exception {
        log.info("Current Repetition:{}", repetitionInfo.getCurrentRepetition());
        TestTradingPartnerRequest testTradingPartnerRequest = requests.get(repetitionInfo.getCurrentRepetition()-1);
        validateGetTradingPartnerById(testTradingPartnerRequest);
    }

    private void validateGetTradingPartnerById(TestTradingPartnerRequest testTradingPartnerRequest) {
        TradingPartnerDto expectedTradingPartner = testTradingPartnerRequest.getTradingPartnerDtoResponse();
        UserDto userDto = testTradingPartnerRequest.getUserDto();
        String tradingPartnerId = testTradingPartnerRequest.getTradingPartnerDto().getTradingPartnerId();
        if(!testTradingPartnerRequest.isExceptionExpected()){
            ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                    .withBasicAuth(userDto.getUsername(), userDto.getPassword())
                    .getForEntity("/api/v1/tp/"+tradingPartnerId, ZeusApiResponse.class);
            ZeusApiResponse actualResponse = responseEntity.getBody();
            TradingPartnerDto actualTradingPartner =
                    objectMapper.convertValue(actualResponse.getResponse(), TradingPartnerDto.class);
            validateTradingPartner(expectedTradingPartner, actualTradingPartner);
        }else{
            if(testTradingPartnerRequest.isAuthException()){
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(userDto.getUsername(), userDto.getPassword())
                        .getForEntity("/api/v1/tp/"+tradingPartnerId, ZeusApiResponse.class);
                assertEquals(testTradingPartnerRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }

    private void validateTradingPartner(TradingPartnerDto expectedTradingPartner, TradingPartnerDto actualTradingPartner) {
        assertEquals(expectedTradingPartner.getTradingPartnerId(), actualTradingPartner.getTradingPartnerId());
        assertEquals(expectedTradingPartner.getDescription(), actualTradingPartner.getDescription());
        assertEquals(expectedTradingPartner.getName(), actualTradingPartner.getName());
        assertEquals(expectedTradingPartner.getSenderId(), actualTradingPartner.getSenderId());
        assertEquals(expectedTradingPartner.getReceiverId(), actualTradingPartner.getReceiverId());
        assertEquals(expectedTradingPartner.getStateTypeCode(), actualTradingPartner.getStateTypeCode());
        assertEquals(expectedTradingPartner.getMarketplaceTypeCode(), actualTradingPartner.getMarketplaceTypeCode());
        assertEquals(expectedTradingPartner.getLineOfBusinessTypeCode(), actualTradingPartner.getLineOfBusinessTypeCode());
    }


//    private static Stream<Arguments> generator(){
//        return Stream.of(
//                Arguments.of(staticRequest)
//        );
//    }

    private void validateGetAllTradingPartners(TestTradingPartnerRequest testTradingPartnerRequest) {
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
            expectedTradingPartnerList.getTradingPartnerDtos().stream().forEach(tradingPartnerDto -> {
                tradingPartnerList.getTradingPartnerDtos().stream().forEach(actualTradingPartnerDto -> {
                    if(tradingPartnerDto.equals(actualTradingPartnerDto)){
                        log.info("About to compare the trading partner details");
                        validateTradingPartner(tradingPartnerDto, actualTradingPartnerDto);
                    }
                });
            });
        }else{
            if(testTradingPartnerRequest.isAuthException()){
                ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                        .withBasicAuth(userDto.getUsername(), userDto.getPassword())
                        .getForEntity("/api/v1/tp", ZeusApiResponse.class);
                assertEquals(testTradingPartnerRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
            }
        }
    }

    private void validateCreateTradingPartner(TestTradingPartnerRequest testTradingPartnerRequest) throws Exception {
        UserDto userDto = testTradingPartnerRequest.getUserDto();
        TradingPartnerDto tradingPartnerDto = testTradingPartnerRequest.getTradingPartnerDto();
        if(!testTradingPartnerRequest.isExceptionExpected()){
            String inputTP = objectMapper.writeValueAsString(tradingPartnerDto);
//            MvcResult mvcResult = mockMvc.perform(post("/api/v1/tp")
//                    .with(httpBasic(userDto.getUsername(), userDto.getPassword()))
//                    .contentType(MediaType.APPLICATION_JSON).content(inputTP))
//                    .andExpect(status().isCreated())
//                    .andReturn();
//            String savedTP = mvcResult.getResponse().getContentAsString();
//            ZeusApiResponse<TradingPartnerDto> apiResponse = objectMapper.readValue(savedTP, new TypeReference<ZeusApiResponse<TradingPartnerDto>>() {});

            ResponseEntity<ZeusApiResponse> responseEntity = testRestTemplate
                    .withBasicAuth(userDto.getUsername(), userDto.getPassword())
                    .postForEntity("/api/v1/tp",tradingPartnerDto, ZeusApiResponse.class);
            ZeusApiResponse apiResponse = responseEntity.getBody();
            TradingPartnerDto savedTradingPartner =
                    objectMapper.convertValue(apiResponse.getResponse(), TradingPartnerDto.class);
            log.info("Saved TP:{}", savedTradingPartner);

            log.info("API Response:{}", apiResponse);
            assertNotNull(apiResponse.getResponse());
            //TradingPartnerDto savedTradingPartner = apiResponse.getResponse();
            assertEquals(tradingPartnerDto.getTradingPartnerId(), savedTradingPartner.getTradingPartnerId());
            assertEquals(tradingPartnerDto.getName(), savedTradingPartner.getName());
            assertEquals(tradingPartnerDto.getDescription(), savedTradingPartner.getDescription());
            assertEquals(tradingPartnerDto.getSenderId(), savedTradingPartner.getSenderId());
            assertEquals(tradingPartnerDto.getReceiverId(), savedTradingPartner.getReceiverId());
            assertEquals(tradingPartnerDto.getLineOfBusinessTypeCode(), savedTradingPartner.getLineOfBusinessTypeCode());
            assertEquals(tradingPartnerDto.getMarketplaceTypeCode(), savedTradingPartner.getMarketplaceTypeCode());
            assertEquals(tradingPartnerDto.getStateTypeCode(), savedTradingPartner.getStateTypeCode());
            tradingPartnerRepository.deleteById(savedTradingPartner.getTradingPartnerSK());
        }else{
            if(testTradingPartnerRequest.isAuthException()){
                log.info("Auth Exception:{}", userDto.getUsername());
//                ResponseEntity responseEntity = testRestTemplate
//                        .withBasicAuth(userDto.getUsername(), userDto.getPassword())
//                        .postForEntity("/api/v1/tp",tradingPartnerDto, ZeusApiResponse.class);
//                assertEquals(testTradingPartnerRequest.getExceptionMessage(), responseEntity.getStatusCode().toString());
//                String inputTP = objectMapper.writeValueAsString(tradingPartnerDto);
//                MvcResult mvcResult = mockMvc.perform(post("/api/v1/tp")
//                                .with(httpBasic(userDto.getUsername(), userDto.getPassword()))
//                                .contentType(MediaType.APPLICATION_JSON).content(inputTP))
//                        //.andExpect(status().isCreated())
//                        .andReturn();
//                log.info("Error message:{}",mvcResult.getResponse());
//                ZeusApiResponse apiResponse =
//                        testRestTemplate.withBasicAuth(userDto.getUsername(), userDto.getPassword()).postForObject("/api/v1/tp",tradingPartnerDto, ZeusApiResponse.class);
                HttpHeaders headers = new HttpHeaders();
                //String inputTP = objectMapper.writeValueAsString(tradingPartnerDto);
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity httpEntity = new HttpEntity(tradingPartnerDto, headers);
                ResponseEntity apiResponse =
                        testRestTemplate.withBasicAuth(userDto.getUsername(), userDto.getPassword()).exchange("/api/v1/tp", HttpMethod.POST, httpEntity, Object.class);
                assertEquals(testTradingPartnerRequest.getExceptionMessage(), apiResponse.getStatusCode().toString());
                //testRestTemplate.e
            }
        }
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
