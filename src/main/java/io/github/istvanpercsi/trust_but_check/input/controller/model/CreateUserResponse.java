package io.github.istvanpercsi.trust_but_check.input.controller.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.istvanpercsi.trust_but_check.service.model.literal.LoginName;

public class CreateUserResponse {
    private final LoginName loginName;

    @JsonCreator
    public CreateUserResponse(LoginName loginName) {
        this.loginName = loginName;
    }

    @JsonValue
    public LoginName getLoginName() {
        return loginName;
    }

    public static CreateUserResponseBuilder builder() {
        return new CreateUserResponseBuilder();
    }

    public static final class CreateUserResponseBuilder {
        private LoginName loginName;

        private CreateUserResponseBuilder() {
        }

        public CreateUserResponseBuilder loginName(LoginName loginName) {
            this.loginName = loginName;
            return this;
        }

        public CreateUserResponse build() {
            return new CreateUserResponse(loginName);
        }
    }
}
