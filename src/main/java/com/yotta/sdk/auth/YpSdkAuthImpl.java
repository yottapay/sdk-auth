package com.yotta.sdk.auth;

import com.yotta.sdk.auth.domain.YpAuthConsentStatus;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorization;
import com.yotta.sdk.auth.domain.YpAuthCreateAuthorizationResult;
import com.yotta.sdk.auth.domain.YpAuthUserDeliveryAddress;
import com.yotta.sdk.core.req.YpRequest;
import org.jetbrains.annotations.NotNull;

public class YpSdkAuthImpl implements YpSdkAuth {

    private final YpRequest<YpAuthCreateAuthorization, YpAuthCreateAuthorizationResult> createAuthorizationRequest;
    private final YpRequest<String, YpAuthConsentStatus> consentStatusRequest;
    private final YpRequest<String, String> exchangeTokenRequest;
    private final YpRequest<String, String> scopeEmailRequest;
    private final YpRequest<String, String> scopeFullNameRequest;
    private final YpRequest<String, String> scopePhoneNumberRequest;
    private final YpRequest<String, YpAuthUserDeliveryAddress> scopeDeliveryAddressRequest;

    public YpSdkAuthImpl(
            @NotNull YpRequest<YpAuthCreateAuthorization, YpAuthCreateAuthorizationResult> createAuthorizationRequest,
            @NotNull YpRequest<String, YpAuthConsentStatus> consentStatusRequest,
            @NotNull YpRequest<String, String> exchangeTokenRequest,
            @NotNull YpRequest<String, String> scopeEmailRequest,
            @NotNull YpRequest<String, String> scopeFullNameRequest,
            @NotNull YpRequest<String, String> scopePhoneNumberRequest,
            @NotNull YpRequest<String, YpAuthUserDeliveryAddress> scopeDeliveryAddressRequest) {
        this.createAuthorizationRequest = createAuthorizationRequest;
        this.consentStatusRequest = consentStatusRequest;
        this.exchangeTokenRequest = exchangeTokenRequest;
        this.scopeEmailRequest = scopeEmailRequest;
        this.scopeFullNameRequest = scopeFullNameRequest;
        this.scopePhoneNumberRequest = scopePhoneNumberRequest;
        this.scopeDeliveryAddressRequest = scopeDeliveryAddressRequest;
    }

    @Override
    public YpAuthCreateAuthorizationResult createAuthorization(YpAuthCreateAuthorization request) {
        return createAuthorizationRequest.sendRequest(request);
    }

    @Override
    public YpAuthConsentStatus checkConsentStatus(String token) {
        return consentStatusRequest.sendRequest(token);
    }

    @Override
    public String exchangeToken(String token) {
        return exchangeTokenRequest.sendRequest(token);
    }

    @Override
    public String getEmail(String token) {
        return scopeEmailRequest.sendRequest(token);
    }

    @Override
    public String getFullName(String token) {
        return scopeFullNameRequest.sendRequest(token);
    }

    @Override
    public String getPhoneNumber(String token) {
        return scopePhoneNumberRequest.sendRequest(token);
    }

    @Override
    public YpAuthUserDeliveryAddress getDeliveryAddress(String token) {
        return scopeDeliveryAddressRequest.sendRequest(token);
    }
}
