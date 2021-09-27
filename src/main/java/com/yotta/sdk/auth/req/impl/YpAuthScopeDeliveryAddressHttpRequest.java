package com.yotta.sdk.auth.req.impl;

import com.yotta.sdk.auth.domain.YpAuthUserDeliveryAddress;
import com.yotta.sdk.auth.req.YpAbstractAuthScopeHttpRequest;
import com.yotta.sdk.core.domain.YpServiceResponse;
import com.yotta.sdk.core.service.YpCloseableHttpClientSupplierService;
import com.yotta.sdk.core.service.YpObjectMapperService;
import org.jetbrains.annotations.NotNull;

public class YpAuthScopeDeliveryAddressHttpRequest extends YpAbstractAuthScopeHttpRequest<YpAuthUserDeliveryAddress> {


    public YpAuthScopeDeliveryAddressHttpRequest(@NotNull String url,
                                                 @NotNull YpObjectMapperService objectMapperService,
                                                 @NotNull YpCloseableHttpClientSupplierService httpClientSupplier,
                                                 @NotNull String username,
                                                 @NotNull String password) {
        super(url, objectMapperService, httpClientSupplier, username, password);
    }

    @Override
    protected YpAuthUserDeliveryAddress onOk(String input, YpServiceResponse response) {
        return convertJsonToObject(response.getResponseBody(), YpAuthUserDeliveryAddress.class);
    }
}
