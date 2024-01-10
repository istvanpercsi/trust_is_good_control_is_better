package io.github.istvanpercsi.trust_but_check.input.controller;

import io.github.istvanpercsi.trust_but_check.input.controller.model.CreateUserRequest;
import io.github.istvanpercsi.trust_but_check.input.controller.model.CreateUserResponse;
import io.github.istvanpercsi.trust_but_check.service.interfaces.input.UserService;
import io.github.istvanpercsi.trust_but_check.service.model.literal.LoginName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(params = "/user")
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
        LoginName loginName = userService.createUser(createUserRequest.getLoginName(), createUserRequest.getName(), createUserRequest.getFamilyName());
        CreateUserResponse createUserResponse = CreateUserResponse.builder()
                .loginName(loginName)
                .build();
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }
}
