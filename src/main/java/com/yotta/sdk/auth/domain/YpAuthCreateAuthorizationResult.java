package com.yotta.sdk.auth.domain;

import lombok.Data;

@Data
public class YpAuthCreateAuthorizationResult {

    private String token;

    private String redirectUrl;
}
