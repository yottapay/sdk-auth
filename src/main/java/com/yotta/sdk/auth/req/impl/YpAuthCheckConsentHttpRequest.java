package com.yotta.sdk.auth.req.impl;

import com.yotta.sdk.auth.domain.YpAuthConsentStatus;
import com.yotta.sdk.auth.req.YpAbstractAuthHttpRequest;
import com.yotta.sdk.core.domain.YpServiceResponse;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import lombok.Data;
import org.apache.http.client.methods.HttpRequestBase;
import org.jetbrains.annotations.NotNull;

public class YpAuthCheckConsentHttpRequest extends YpAbstractAuthHttpRequest<String, YpAuthConsentStatus> {

    public YpAuthCheckConsentHttpRequest(@NotNull String url,
                                         @NotNull YpObjectMapperService objectMapper,
                                         @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
                                         @NotNull String username, @NotNull String password) {
        super(url, objectMapper, httpClientSupplier, username, password);
    }

    @Override
    protected HttpRequestBase createRequestObject(String token) {
        return createEmptyGetRequest(token);
    }

    @Override
    protected void addHeaders(HttpRequestBase request, String token) {
        super.addHeaders(request, token);
        addUserConsentHeader(request, token);
    }

    @Override
    protected YpAuthConsentStatus onOk(String token, YpServiceResponse response) {
        Response result = convertJsonToObject(response.getResponseBody(), Response.class);
        return result.getExtConsentState();
    }

    @Override
    protected YpAuthConsentStatus onNotFound(String token, YpServiceResponse response) {
        return YpAuthConsentStatus.NOT_FOUND;
    }

    @Data
    private static class Response {
        private YpAuthConsentStatus extConsentState;
    }
}
