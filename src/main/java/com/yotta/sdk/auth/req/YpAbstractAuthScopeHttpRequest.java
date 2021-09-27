package com.yotta.sdk.auth.req;


import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import org.apache.http.client.methods.HttpRequestBase;
import org.jetbrains.annotations.NotNull;

public abstract class YpAbstractAuthScopeHttpRequest<T_OUT> extends YpAbstractAuthHttpRequest<String, T_OUT> {

    public YpAbstractAuthScopeHttpRequest(
            @NotNull String url,
            @NotNull YpObjectMapperService objectMapperService,
            @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
            @NotNull String username,
            @NotNull String password) {
        super(url, objectMapperService, httpClientSupplier, username, password);
    }

    @Override
    protected HttpRequestBase createRequestObject(String input) {
        return createEmptyGetRequest(input);
    }

    @Override
    protected void addHeaders(HttpRequestBase request, String userConsent) {
        super.addHeaders(request, userConsent);
        addUserConsentHeader(request, userConsent);
    }
}
