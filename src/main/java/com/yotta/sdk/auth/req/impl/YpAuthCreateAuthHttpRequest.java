package com.yotta.sdk.auth.req.impl;

import com.yotta.sdk.auth.domain.YpAuthCreateAuthorization;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorizationResult;
import com.yotta.sdk.auth.req.YpAbstractAuthHttpRequest;
import com.yotta.sdk.core.domain.YpServiceResponse;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import org.apache.http.client.methods.HttpRequestBase;
import org.jetbrains.annotations.NotNull;

public class YpAuthCreateAuthHttpRequest extends YpAbstractAuthHttpRequest<YpAuthCreateAuthorization, YpAuthCreateAuthorizationResult> {


    public YpAuthCreateAuthHttpRequest(@NotNull String url,
                                       @NotNull YpObjectMapperService objectMapper,
                                       @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
                                       @NotNull String username, @NotNull String password) {
        super(url, objectMapper, httpClientSupplier, username, password);
    }

    @Override
    protected HttpRequestBase createRequestObject(YpAuthCreateAuthorization input) {
        return createEmptyPostRequest(input);
    }

    @Override
    protected YpAuthCreateAuthorizationResult onOk(YpAuthCreateAuthorization request,
                                                   YpServiceResponse response) {
        return convertJsonToObject(response.getResponseBody(), YpAuthCreateAuthorizationResult.class);
    }
}
