package com.yotta.sdk.auth;

import com.yotta.sdk.auth.config.YpSdkAuthConfiguration;
import com.yotta.sdk.auth.domain.YpAuthConsentStatus;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorization;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorizationResult;
import com.yotta.sdk.auth.domain.YpAuthUserDeliveryAddress;
import com.yotta.sdk.auth.req.impl.*;
import com.yotta.sdk.core.exception.YpRequiredPropertyException;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import org.jetbrains.annotations.NotNull;

public interface YpSdkAuth {

    static @NotNull YpSdkAuth createDefault(@NotNull(exception = YpRequiredPropertyException.class) String username,
                                            @NotNull(exception = YpRequiredPropertyException.class) String password) {
        YpSdkAuthConfiguration configuration = YpSdkAuthConfiguration.createDefault();
        configuration.setUsername(username);
        configuration.setPassword(password);

        return createFromConfiguration(configuration);
    }

    static @NotNull YpSdkAuth createFromConfiguration(
            @NotNull YpSdkAuthConfiguration configuration) throws YpRequiredPropertyException {
        String username = configuration.getUsername();
        String password = configuration.getPassword();
        String serverBaseUrl = configuration.getServerBaseUrl();

        YpObjectMapperService objectMapperService = YpObjectMapperService.DEFAULT;
        YpCloseableHttpClientSupplierService httpClientSupplierService = YpCloseableHttpClientSupplierService.DEFAULT;

        return new YpSdkAuthImpl(
                new YpAuthCreateAuthHttpRequest(
                        serverBaseUrl + configuration.getCreateAuthEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                ),
                new YpAuthCheckConsentHttpRequest(
                        serverBaseUrl + configuration.getCheckConsentEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                ),
                new YpAuthExchangeTokenHttpRequest(
                        serverBaseUrl + configuration.getExchangeTokenEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                ),
                new YpAuthScopeEmailHttpRequest(
                        serverBaseUrl + configuration.getEmailEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                ),
                new YpAuthScopeFullNameHttpRequest(
                        serverBaseUrl + configuration.getFullNameEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                ),
                new YpAuthScopePhoneNumberHttpRequest(
                        serverBaseUrl + configuration.getPhoneNumberEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                ),
                new YpAuthScopeDeliveryAddressHttpRequest(
                        serverBaseUrl + configuration.getDeliveryAddressEndpoint(),
                        objectMapperService,
                        httpClientSupplierService,
                        username,
                        password
                )
        );
    }

    YpAuthCreateAuthorizationResult createAuthorization(YpAuthCreateAuthorization request);

    YpAuthConsentStatus checkConsentStatus(String token);

    String exchangeToken(String token);

    String getEmail(String token);

    String getFullName(String token);

    String getPhoneNumber(String token);

    YpAuthUserDeliveryAddress getDeliveryAddress(String token);
}
