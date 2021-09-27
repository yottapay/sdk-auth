package com.yotta.sdk.auth.domain;

public enum YpAuthUserScope {
    SCOPE_FULL_NAME("ext.fullname"),

    SCOPE_EMAIL("ext.realemail"),

    SCOPE_PHONE_NUMBER("ext.phone"),

    SCOPE_DELIVERY_ADDRESS("ext.deliveryaddr");


    private final String scope;


    YpAuthUserScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
