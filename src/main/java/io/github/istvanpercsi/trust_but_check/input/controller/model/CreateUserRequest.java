package io.github.istvanpercsi.trust_but_check.input.controller.model;

import io.github.istvanpercsi.trust_but_check.service.model.literal.FamilyName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.LoginName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.Name;

public class CreateUserRequest {

    private final LoginName loginName;
    private final Name name;
    private final FamilyName familyName;


    public CreateUserRequest(LoginName loginName, Name name, FamilyName familyName) {
        this.loginName = loginName;
        this.name = name;
        this.familyName = familyName;
    }

    public LoginName getLoginName() {
        return loginName;
    }

    public Name getName() {
        return name;
    }

    public FamilyName getFamilyName() {
        return familyName;
    }
}
