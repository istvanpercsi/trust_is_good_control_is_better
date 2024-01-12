package io.github.istvanpercsi.trust_but_check.service.interfaces.input;

import io.github.istvanpercsi.trust_but_check.service.model.literal.FamilyName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.LoginName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.Name;

public interface UserService {

    LoginName createUser(LoginName loginName, Name name, FamilyName familyName);
}
