package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.*;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.AuthorityDto;
import com.brihaspathee.zeus.web.security.AuthorityList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, February 2022
 * Time: 6:07 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/tp/authority")
@Validated
public interface AuthorityApi {

    @Operation(
            method = "GET",
            description = "Get all the requested authorities in the system",
            tags = {"authorities"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the authorities",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @AuthorityReadPermission
    public ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorities(@RequestParam(required = false) UUID authorityId,
                                                                   @RequestParam(required = false) String permission);

    @Operation(
            method = "POST",
            description = "Method to create a new authority",
            tags = {"authorities"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the authority",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = {
                                    @Content(mediaType = "application/json",schema = @Schema(implementation = ZeusApiResponse.class))
                            }),
                    @ApiResponse(responseCode = "409",
                            description = "Conflict",
                            content = {
                                    @Content(mediaType = "application/json",schema = @Schema(implementation = ZeusApiResponse.class))
                            })
            }
    )
    @PostMapping
    @AuthorityCreatePermission
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> createAuthority(@RequestBody AuthorityDto authorityDto);

    @Operation(
            method = "PUT",
            description = "Update an existing authority",
            tags = {"authorities"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully updated the authority",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ZeusApiResponse.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ZeusApiResponse.class))
                    }),
            @ApiResponse(responseCode = "409",
                    description = "Conflict",
                    content = {
                            @Content(mediaType = "application/json",schema = @Schema(implementation = ZeusApiResponse.class))
                    })
    })
    @PutMapping("/{authorityId}")
    @AuthorityUpdatePermission
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> updateAuthority(@RequestBody AuthorityDto authorityDto, @PathVariable("authorityId") UUID authorityId);
}
