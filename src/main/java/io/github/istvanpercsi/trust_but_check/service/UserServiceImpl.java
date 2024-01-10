package io.github.istvanpercsi.trust_but_check.service;

import io.github.istvanpercsi.trust_but_check.service.interfaces.input.UserService;
import io.github.istvanpercsi.trust_but_check.service.model.literal.FamilyName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.LoginName;
import io.github.istvanpercsi.trust_but_check.service.model.literal.Name;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public LoginName createUser(LoginName loginName, Name name, FamilyName familyName) {
        return null;
    }
}
