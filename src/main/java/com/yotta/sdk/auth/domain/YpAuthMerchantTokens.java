package com.yotta.sdk.auth.domain;

import lombok.Data;

@Data
public class YpAuthMerchantTokens {
    private String identity;
    private String secret;
}
