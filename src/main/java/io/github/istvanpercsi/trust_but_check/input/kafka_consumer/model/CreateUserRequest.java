package io.github.istvanpercsi.trust_but_check.input.kafka_consumer.model;

import io.github.istvanpercsi.trust_but_check.service.model.literal.FamilyName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.LoginName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.Name;
import io.github.istvanpercsi.trust_but_check.service.model.literal.PersonalNr;

public class CreateUserRequest {

    private final LoginName loginName;
    private final Name name;
    private final FamilyName familyName;
    private final PersonalNr personalNr;


    public CreateUserRequest(LoginName loginName, Name name, FamilyName familyName, PersonalNr personalNr) {
        this.loginName = loginName;
        this.name = name;
        this.familyName = familyName;
        this.personalNr = personalNr;
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

    public PersonalNr getPersonalNr() {
        return personalNr;
    }
}
