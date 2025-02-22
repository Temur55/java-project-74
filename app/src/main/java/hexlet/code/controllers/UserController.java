package hexlet.code.controllers;


import hexlet.code.dto.UserDto;
import hexlet.code.model.User;
import hexlet.code.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static hexlet.code.controllers.UserController.USER_CONTROLLER_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping ("${base-url}" + USER_CONTROLLER_PATH)
@AllArgsConstructor
public class UserController {
    public static final String USER_CONTROLLER_PATH = "/users";
    private static final String OWNER = "@userRepository.findById(#id).get().getEmail() == authentication.getName()";
    public static final String ID = "/{id}";
    @Autowired
    private UserService userService;


    @Operation(summary = "Get user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Information retrieved",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(path = ID)
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Get list of Users")
    @ApiResponses(@ApiResponse(responseCode = "200", content =
    @Content(schema = @Schema(implementation = User.class))
    ))
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @PostMapping
    @ResponseStatus(CREATED)
    public User registerNew(@RequestBody @Valid final UserDto userDto) throws Exception {
        return userService.createUser(userDto);
    }

    @Operation(summary = "Update user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "Incorrect user data")})
    @PreAuthorize(OWNER)
    @PutMapping(path = ID)
    public User updateUser(@PathVariable long id, @RequestBody @Valid final UserDto userDto) throws Exception {
        return userService.updateUser(id, userDto);
    }

    @Operation(summary = "Delete user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "422", description = "User is connected to at least one task")})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(OWNER)
    @DeleteMapping(path = ID)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
