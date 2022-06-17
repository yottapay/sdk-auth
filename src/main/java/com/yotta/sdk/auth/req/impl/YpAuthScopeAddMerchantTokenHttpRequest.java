package com.yotta.sdk.auth.req.impl;

import com.yotta.sdk.auth.domain.YpAuthMerchantTokens;
import com.yotta.sdk.auth.req.YpAbstractAuthScopeHttpRequest;
import com.yotta.sdk.core.domain.YpServiceResponse;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import org.jetbrains.annotations.NotNull;

public class YpAuthScopeAddMerchantTokenHttpRequest extends YpAbstractAuthScopeHttpRequest<YpAuthMerchantTokens> {

    public YpAuthScopeAddMerchantTokenHttpRequest(@NotNull String url,
                                                  @NotNull YpObjectMapperService objectMapperService,
                                                  @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
                                                  @NotNull String username,
                                                  @NotNull String password) {
        super(url, objectMapperService, httpClientSupplier, username, password);
    }

    @Override
    protected YpAuthMerchantTokens onOk(String input, YpServiceResponse response) {
        return convertJsonToObject(response.getResponseBody(), YpAuthMerchantTokens.class);
    }
}
