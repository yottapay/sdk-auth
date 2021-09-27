package com.yotta.sdk.auth.req.impl;

import com.yotta.sdk.auth.req.YpAbstractAuthScopeHttpRequest;
import com.yotta.sdk.core.domain.YpServiceResponse;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public class YpAuthScopeEmailHttpRequest extends YpAbstractAuthScopeHttpRequest<String> {


    public YpAuthScopeEmailHttpRequest(@NotNull String url,
                                       @NotNull YpObjectMapperService objectMapperService,
                                       @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
                                       @NotNull String username,
                                       @NotNull String password) {
        super(url, objectMapperService, httpClientSupplier, username, password);
    }

    @Override
    protected String onOk(String input, YpServiceResponse response) {
        return convertJsonToObject(response.getResponseBody(), Response.class).getEmail();
    }

    @Data
    private static class Response {
        private String email;
    }
}
