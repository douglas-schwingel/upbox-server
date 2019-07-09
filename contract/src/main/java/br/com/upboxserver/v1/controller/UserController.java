package br.com.upboxserver.v1.controller;

import br.com.upboxserver.v1.user.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(tags = "User", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/v1/user")
public class UserController {

    private final UserControllerFacade facade;

    public UserController(UserControllerFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "Save a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public @NotNull User saveUser(@RequestBody @NotNull User user) {
        return facade.saveUser(user);
    }


    @ApiOperation(value="Find user by username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User retrivied", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/{username}")
    public @NotNull User findUser(@PathVariable("username") @NotNull String username) {
        return facade.find(username);
    }

    @ApiOperation(value="Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User delete", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @DeleteMapping("/{username}")
    public @NotNull User deleteUser(@PathVariable("username") @NotNull String username) {
        return facade.delete(username);
    }

    @ApiOperation(value="Update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated", response = User.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PatchMapping("/{username}")
    public @NotNull User updateUser(@PathVariable("username") String userToBeUpdated,
                                    @RequestBody @NotNull User user) {
        return facade.update(userToBeUpdated, user);
    }


}
