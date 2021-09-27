package com.yotta.sdk.auth.req;

import com.yotta.sdk.auth.config.YpSdkAuthConfiguration;
import com.yotta.sdk.core.req.YpAbstractHttpRequest;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import org.apache.http.Header;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.auth.BasicScheme;
import org.jetbrains.annotations.NotNull;

public abstract class YpAbstractAuthHttpRequest<T_IN, T_OUT> extends YpAbstractHttpRequest<T_IN, T_OUT> {

    public static final String HEADER_USER_CONSENT_NAME = "User-Consent";
    private final String username;
    private final String password;

    public YpAbstractAuthHttpRequest(
            @NotNull String url,
            @NotNull YpObjectMapperService objectMapper,
            @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
            @NotNull String username,
            @NotNull String password) {
        super(url, objectMapper, httpClientSupplier);
        this.username = username;
        this.password = password;
    }

    @Override
    protected final String getSdkName() {
        return YpSdkAuthConfiguration.SDK_NAME;
    }

    @Override
    protected void addHeaders(HttpRequestBase request, T_IN input) {
        super.addHeaders(request, input);
        addAuthorizationHeader(request);
    }

    protected void addAuthorizationHeader(HttpRequestBase request) {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(getUsername(), getPassword());
        Header header;
        try {
            header = new BasicScheme().authenticate(credentials, request, null);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        request.addHeader(header);
    }

    protected void addUserConsentHeader(HttpRequestBase request, String value) {
        request.addHeader(HEADER_USER_CONSENT_NAME, value);
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }
}
