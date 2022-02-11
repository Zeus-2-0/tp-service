package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.UserCreatePermission;
import com.brihaspathee.zeus.permissions.UserReadPermission;
import com.brihaspathee.zeus.permissions.UserUpdatePermission;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.response.ApiExceptionList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
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
 * Time: 4:24 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/tp/user")
@Validated
public interface UserApi {

    /**
     * Method to get a user or return all users
     * @param userId
     * @param username
     * @return
     */
    @Operation(
            method = "GET",
            description = "Get a user or all users in the system",
            tags = {"user"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the users",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @UserReadPermission
    public ResponseEntity<ZeusApiResponse<UserList>> getUser(@RequestParam (required = false) UUID userId,
                                                             @RequestParam (required = false) String username);

    /**
     * Method to create a new user in the system
     * @param userDto
     * @return
     */
    @Operation(
            method = "POST",
            description = "Method to create a new user",
            tags = {"user"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the user",
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
    @UserCreatePermission
    public ResponseEntity<ZeusApiResponse<UserDto>> createUser(@RequestBody UserDto userDto);

    /**
     * Method to update the user
     * @param userDto
     * @return
     */
    @Operation(
            method = "PUT",
            description = "Update an existing user",
            tags = {"user"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully updated the user",
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
    @PutMapping("/{userId}")
    @UserUpdatePermission
    public ResponseEntity<ZeusApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") UUID userId);
}
