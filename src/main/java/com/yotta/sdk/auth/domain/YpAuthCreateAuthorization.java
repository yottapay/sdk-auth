package com.yotta.sdk.auth.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class YpAuthCreateAuthorization {

    private Set<String> scopes = new HashSet<>();

    private String successUrl;

    private String failUrl;

    public void addScopes(YpAuthUserScope... scopes) {
        for (YpAuthUserScope scope : scopes) {
            this.scopes.add(scope.getScope());
        }
    }
}
