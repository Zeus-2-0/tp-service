package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.TradingPartnerReadPermission;
import com.brihaspathee.zeus.web.response.ApiExceptionList;
import com.brihaspathee.zeus.permissions.TradingPartnerCreatePermission;
import com.brihaspathee.zeus.permissions.TradingPartnerUpdatePermission;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 14, January 2022
 * Time: 9:43 AM
 * Project: Zeus
 * Package Name: com.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/tp")
@Validated
public interface TradingPartnerApi {

    @Operation(
            method = "GET",
            description = "Get All the trading partner in the system",
            tags = {"trading-partner"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the trading partners",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TradingPartnerList.class))
                            }
                    )
            }
    )
    @TradingPartnerReadPermission
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllTradingPartners();

    /**
     *
     * @param page
     * @param pageSize
     * @param sortBy
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get trading partners sorted and option use pagination",
            tags = {"trading-partner"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved all trading partners",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = TradingPartnerList.class))
                    })
    })
    @TradingPartnerReadPermission
    @GetMapping("/sort")
    ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllSortedTradingPartners(
            @Parameter(in = ParameterIn.QUERY, description = "Page number requested" ,schema=@Schema( defaultValue="0"), required = false)  @RequestParam Optional<Integer> page,
            @Parameter(in = ParameterIn.QUERY, description = "Page size for the page" ,schema=@Schema( defaultValue="5"), required = false) @RequestParam Optional<Integer> pageSize,
            @Parameter(in = ParameterIn.QUERY, description = "Sorting column" ,schema=@Schema( defaultValue="tradingPartnerId"), required = false) @RequestParam Optional<String> sortBy);

    /**
     *
     * @param tradingPartnerDto
     * @return
     */
    @Operation(
            method = "POST",
            description = "Create a new trading partner",
            tags = {"trading-partner"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully created the trading partner",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = TradingPartnerDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @TradingPartnerCreatePermission
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZeusApiResponse<TradingPartnerDto>> createTradingPartner(@RequestBody @Valid TradingPartnerDto tradingPartnerDto) throws JsonProcessingException;

    /**
     *
     * @param tradingPartnerDto
     * @param tradingPartnerSK
     * @return
     */
    @Operation(
            method = "PUT",
            description = "Update a new trading partner",
            tags = {"trading-partner"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully updated the trading partner",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = TradingPartnerDto.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                    })
    })
    @TradingPartnerUpdatePermission
    @PutMapping("/{tradingPartnerSK}")
    ResponseEntity<ZeusApiResponse<TradingPartnerDto>> updateTradingPartner(@RequestBody @Valid TradingPartnerDto tradingPartnerDto, @PathVariable("tradingPartnerSK")UUID tradingPartnerSK);

    /**
     *
     * @param tradingPartnerId
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get the trading partner by trading partner id",
            tags = {"trading-partner"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the trading partner",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TradingPartnerDto.class))
                            }
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = {
                                    @Content(mediaType = "application/json",schema = @Schema(implementation = ApiExceptionList.class))
                            }),
            }
    )
    @TradingPartnerReadPermission
    @GetMapping("/{tradingPartnerId}")
    ResponseEntity<ZeusApiResponse<TradingPartnerDto>> getTradingPartnerById(@PathVariable("tradingPartnerId") String tradingPartnerId);
}
