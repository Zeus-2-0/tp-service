package com.brihaspathee.zeus.web.resource.interfaces;

import com.brihaspathee.zeus.permissions.RoleCreatePermission;
import com.brihaspathee.zeus.permissions.RoleReadPermission;
import com.brihaspathee.zeus.permissions.RoleUpdatePermission;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.RoleDto;
import com.brihaspathee.zeus.web.security.RoleList;
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
 * Time: 5:13 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.interfaces
 * To change this template use File | Settings | File and Code Template
 */
@RequestMapping("/api/v1/tp/role")
@Validated
public interface RoleApi {

    @Operation(
            method = "GET",
            description = "Get a roles in the system",
            tags = {"roles"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved all the requested roles",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ZeusApiResponse.class))
                            }
                    )
            }
    )
    @GetMapping
    @RoleReadPermission
    public ResponseEntity<ZeusApiResponse<RoleList>> getRoles(@RequestParam(required = false) UUID roleId,
                                                              @RequestParam(required = false) String roleName);

    @Operation(
            method = "POST",
            description = "Method to create a new role",
            tags = {"roles"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created the role",
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
    @RoleCreatePermission
    public ResponseEntity<ZeusApiResponse<RoleDto>> createRole(@RequestBody RoleDto roleDto);

    @Operation(
            method = "PUT",
            description = "Update an existing role",
            tags = {"roles"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successfully updated the role",
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
    @PutMapping("/{roleId}")
    @RoleUpdatePermission
    public ResponseEntity<ZeusApiResponse<RoleDto>> updateRole(@RequestBody RoleDto roleDto, @PathVariable("roleId") UUID roleId);
}
