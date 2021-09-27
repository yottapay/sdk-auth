package com.yotta.sdk.auth.req.impl;

import com.yotta.sdk.auth.req.YpAbstractAuthHttpRequest;
import com.yotta.sdk.core.domain.YpServiceResponse;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import lombok.Data;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.jetbrains.annotations.NotNull;

public class YpAuthExchangeTokenHttpRequest extends YpAbstractAuthHttpRequest<String, String> {

    public YpAuthExchangeTokenHttpRequest(@NotNull String url,
                                          @NotNull YpObjectMapperService objectMapper,
                                          @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
                                          @NotNull String username, @NotNull String password) {
        super(url, objectMapper, httpClientSupplier, username, password);
    }

    @Override
    protected HttpRequestBase createRequestObject(String input) {
        return new HttpGet(getUrl());
    }

    @Override
    protected void addHeaders(HttpRequestBase request, String token) {
        super.addHeaders(request, token);
        addUserConsentHeader(request, token);
    }

    @Override
    protected String onOk(String token, YpServiceResponse response) {
        return convertJsonToObject(response.getResponseBody(), Response.class).getToken();
    }

    @Data
    private static class Response {
        private String token;
    }
}
