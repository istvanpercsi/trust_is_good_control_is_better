package io.github.istvanpercsi.trust_but_check.service.model.literal;

public class Name {

    private final String value;


    public Name(String value) {
        this.value = validate(value);
    }

    protected String validate(String value) {
        return value;
    }

    public String getValue() {
        return this.value;
    }
}
