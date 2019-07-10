package br.com.upboxserver.v1.user.controller;

import br.com.upboxserver.v1.user.facade.UserFacade;
import br.com.upboxserver.v1.user.model.User;
import io.swagger.annotations.*;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(tags = "User", description = "User operations",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/v1/user")
public class UserController {

    private final UserFacade facade;

    public UserController(UserFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "Save a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @NotNull User saveUser(@RequestBody @NotNull
                                      @ApiParam(value = "User to be saved", required = true) @NonNull User user) {
        return facade.saveUser(user);
    }


    @ApiOperation(value="Find user by username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrivied", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    public @NotNull User findUser(@PathVariable("username") @NotNull
                                      @ApiParam(value = "Username of the user to be found", example = "silvajoao",
                                              required = true) String username) {
        return facade.find(username);
    }

    @ApiOperation(value="Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User delete", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{username}")
    public @NotNull Long deleteUser(@PathVariable("username") @NotNull
                                        @ApiParam(value = "Username of the user to be removed", example = "silvajoao",
                                                required = true)String username) {
        return facade.delete(username);
    }

    @ApiOperation(value="Update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping()
    public @NotNull User updateUser(@RequestBody @NotNull
                                        @ApiParam(value = "User to be updated",
                                                required = true) User user) {
        return facade.update(user);
    }


}
