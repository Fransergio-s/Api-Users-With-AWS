package compasso.com.br.apiuser.controller;

import compasso.com.br.apiuser.model.dto.LoginResponse;
import compasso.com.br.apiuser.model.dto.UserRequestDto;
import compasso.com.br.apiuser.model.dto.UserResponseDto;
import compasso.com.br.apiuser.model.dto.UserUpdatePasswordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "Responsible for creating and updating a user")
@SecurityRequirement(name = "tokenAuth")
@RestController
@RequestMapping("/api/users")
public interface UserController {

    @Operation(summary = "Create a user based on a username, password, email and zip code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok->Created User.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict: User already exists.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Error->User not created.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponse.class)))
            })
    @SecurityRequirement(name = "")
    @PostMapping("/register")
    ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto user);

    @Operation(summary = "Updating a user based on username. Validating whether the old password is correct.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ok->Updated ticket.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found: User not found.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error: Unexpected error occurred.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            })
    @PutMapping("/update-password")
    ResponseEntity<UserResponseDto> update(@RequestBody UserUpdatePasswordDto user);
}
