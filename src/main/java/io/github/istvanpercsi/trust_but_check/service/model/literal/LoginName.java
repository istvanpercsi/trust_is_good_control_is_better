package io.github.istvanpercsi.trust_but_check.service.model.literal;

public class LoginName extends Name {

    public LoginName(String value) {
        super(value);
    }

    @Override
    protected String validate(String value) {
        // here can be implemented some validation for login name.
        return value;
    }
}
