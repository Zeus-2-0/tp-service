package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.exception.ZeusApiValidationException;
import com.brihaspathee.zeus.schema.validation.JSONSchemaObject;
import com.brihaspathee.zeus.schema.validation.JSONSchemaValidator;
import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.resource.interfaces.TradingPartnerApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 14, January 2022
 * Time: 9:52 AM
 * Project: Zeus
 * Package Name: com.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TradingPartnerResource implements TradingPartnerApi {

    private final TradingPartnerService tradingPartnerService;

    private final ObjectMapper objectMapper;

    private final JSONSchemaValidator jsonSchemaValidator;

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllTradingPartners() {
        TradingPartnerList tradingPartnerList = tradingPartnerService.getAllTradingPartners();
        ZeusApiResponse<TradingPartnerList> apiResponse = ZeusApiResponse.<TradingPartnerList>builder()
                .response(tradingPartnerList)
                .message(ApiResponseConstants.SUCCESS)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllSortedTradingPartners(Optional<Integer> page,
                                                                          Optional<Integer> pageSize,
                                                                          Optional<String> sortBy) {
        log.info("page:{}, pageSize:{}, sortBy:{}", page, pageSize, sortBy);
        TradingPartnerList tradingPartnerList = tradingPartnerService
                .getTradingPartnersSorted(
                        PageRequest.of(
                                page.orElse(0),
                                pageSize.orElse(5),
                                Sort.Direction.ASC,
                                sortBy.orElse("tradingPartnerId")));
        ZeusApiResponse<TradingPartnerList> apiResponse = ZeusApiResponse.<TradingPartnerList>builder()
                .response(tradingPartnerList)
                .message(ApiResponseConstants.SUCCESS)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerDto>> createTradingPartner(TradingPartnerDto tradingPartnerDto) throws JsonProcessingException {
        log.info("Inside the controller to create trading partner");
        JSONSchemaObject<TradingPartnerDto> schemaObject = new JSONSchemaObject<>();
        schemaObject.setSchemaObject(tradingPartnerDto);
        InputStream schemaAsStream = TradingPartnerResource.class.getClassLoader().getResourceAsStream("model/tradingPartner.schema.json");
        jsonSchemaValidator.validateJSON(schemaObject,schemaAsStream,ApiResponseConstants.TRADING_PARTNER_API);
        //validateJSON(tradingPartnerDto);

        ZeusApiResponse<TradingPartnerDto> apiResponse = saveTradingPartner(tradingPartnerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/"+apiResponse.getResponse().getTradingPartnerSK());
        return new ResponseEntity<ZeusApiResponse<TradingPartnerDto>>(apiResponse, httpHeaders, HttpStatus.CREATED);
        //return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity updateTradingPartner(TradingPartnerDto tradingPartnerDto, UUID tradingPartnerSK) {
        tradingPartnerDto.setTradingPartnerSK(tradingPartnerSK);
        ZeusApiResponse<TradingPartnerDto> apiResponse = saveTradingPartner(tradingPartnerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/"+apiResponse.getResponse().getTradingPartnerSK());
        return new ResponseEntity<ZeusApiResponse<TradingPartnerDto>>(apiResponse, httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerDto>> getTradingPartnerById(String tradingPartnerId) {
        TradingPartnerDto tradingPartnerDto = tradingPartnerService.getTradingPartnerById(tradingPartnerId);
        ZeusApiResponse<TradingPartnerDto> apiResponse = ZeusApiResponse.<TradingPartnerDto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(tradingPartnerDto)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    private ZeusApiResponse<TradingPartnerDto> saveTradingPartner(TradingPartnerDto tradingPartnerDto){
        TradingPartnerDto savedTP = tradingPartnerService.savingTradingPartner(tradingPartnerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/"+savedTP.getTradingPartnerSK());
        ZeusApiResponse<TradingPartnerDto> apiResponse = ZeusApiResponse.<TradingPartnerDto>builder()
                .response(savedTP)
                .message(ApiResponseConstants.SUCCESS)
                .build();
        return apiResponse;
    }


}
