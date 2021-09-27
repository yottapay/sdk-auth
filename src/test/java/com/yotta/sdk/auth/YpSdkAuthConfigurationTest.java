package com.yotta.sdk.auth;

import com.yotta.sdk.auth.config.YpSdkAuthConfiguration;
import com.yotta.sdk.auth.config.YpSdkAuthConfiguration.AuthProperties;
import com.yotta.sdk.core.exception.YpRequiredPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class YpSdkAuthConfigurationTest {

    @Test
    public void test_defaultConfiguration() {
        YpSdkAuthConfiguration config = YpSdkAuthConfiguration.createDefault();

        assertEquals(AuthProperties.SERVER_BASE_URL.getDefaultValue(), config.getServerBaseUrl());
        assertEquals(AuthProperties.ENDPOINT_CREATE_AUTH.getDefaultValue(), config.getCreateAuthEndpoint());
        assertEquals(AuthProperties.ENDPOINT_EXCHANGE_TOKEN.getDefaultValue(), config.getExchangeTokenEndpoint());
        assertEquals(AuthProperties.ENDPOINT_CHECK_CONSENT.getDefaultValue(), config.getCheckConsentEndpoint());
        assertEquals(AuthProperties.ENDPOINT_GET_FULL_NAME.getDefaultValue(), config.getFullNameEndpoint());
        assertEquals(AuthProperties.ENDPOINT_GET_EMAIL.getDefaultValue(), config.getEmailEndpoint());
        assertEquals(AuthProperties.ENDPOINT_GET_PHONE_NUMBER.getDefaultValue(), config.getPhoneNumberEndpoint());
        assertEquals(AuthProperties.ENDPOINT_GET_DELIVERY_ADDRESS.getDefaultValue(), config.getDeliveryAddressEndpoint());
    }

    @Test(expected = YpRequiredPropertyException.class)
    public void test_whenUsernameNull_Exception() {
        YpSdkAuthConfiguration.createDefault().getUsername();
    }

    @Test(expected = YpRequiredPropertyException.class)
    public void test_whenPasswordNull_Exception() {
        YpSdkAuthConfiguration.createDefault().getPassword();
    }
}
