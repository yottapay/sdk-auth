package com.yotta.sdk.auth.config;

import com.yotta.sdk.core.config.YpSdkConfiguration;
import com.yotta.sdk.core.exception.YpRequiredPropertyException;
import com.yotta.sdk.core.property.YpStringProperty;
import org.jetbrains.annotations.NotNull;

import static com.yotta.sdk.core.config.YpSdkConfiguration.propertyPath;


public interface YpSdkAuthConfiguration extends YpSdkConfiguration {

    String SDK_NAME = "Yotta Pay SDK (Auth) (1.0.11)";

    static YpSdkAuthConfiguration createDefault() {
        return new YpSdkAuthConfigurationImpl();
    }

    default String getServerBaseUrl()
            throws YpRequiredPropertyException {
        return AuthProperties.SERVER_BASE_URL.get(this);
    }

    default void setServerBaseUrl(String serverBaseUrl)
            throws YpRequiredPropertyException {
        AuthProperties.SERVER_BASE_URL.set(this, serverBaseUrl);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getUsername()
            throws YpRequiredPropertyException {
        return AuthProperties.CREDENTIALS_USERNAME.get(this);
    }

    default void setUsername(
            @NotNull(exception = YpRequiredPropertyException.class) String username)
            throws YpRequiredPropertyException {
        AuthProperties.CREDENTIALS_USERNAME.set(this, username);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getPassword()
            throws YpRequiredPropertyException {
        return AuthProperties.CREDENTIALS_PASSWORD.get(this);
    }

    default void setPassword(
            @NotNull(exception = YpRequiredPropertyException.class) String password)
            throws YpRequiredPropertyException {
        AuthProperties.CREDENTIALS_PASSWORD.set(this, password);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getCheckConsentEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_CHECK_CONSENT.get(this);
    }

    default void setCheckConsentEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String checkConsentEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_CHECK_CONSENT.set(this, checkConsentEndpoint);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getCreateAuthEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_CREATE_AUTH.get(this);
    }

    default void setCreateAuthEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String createAuthEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_CREATE_AUTH.set(this, createAuthEndpoint);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getExchangeTokenEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_EXCHANGE_TOKEN.get(this);
    }

    default void setExchangeTokenEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String exchangeTokenEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_EXCHANGE_TOKEN.set(this, exchangeTokenEndpoint);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getDeliveryAddressEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_GET_DELIVERY_ADDRESS.get(this);
    }

    default void setDeliveryAddressEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String deliveryAddressEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_GET_DELIVERY_ADDRESS.set(this, deliveryAddressEndpoint);
    }


    @NotNull(exception = YpRequiredPropertyException.class)
    default String getAddMerchantTokensEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_ADD_MERCHANT_KEYS.get(this);
    }


    default void setAddMerchantTokensEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String addMerchantTokensEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_ADD_MERCHANT_KEYS.set(this, addMerchantTokensEndpoint);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getEmailEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_GET_EMAIL.get(this);
    }

    default void setEmailEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String emailEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_GET_EMAIL.set(this, emailEndpoint);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getFullNameEndpoint() throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_GET_FULL_NAME.get(this);
    }

    default void setFullNameEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String fullNameEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_GET_FULL_NAME.set(this, fullNameEndpoint);
    }

    @NotNull(exception = YpRequiredPropertyException.class)
    default String getPhoneNumberEndpoint()
            throws YpRequiredPropertyException {
        return AuthProperties.ENDPOINT_GET_PHONE_NUMBER.get(this);
    }

    default void setPhoneNumberEndpoint(
            @NotNull(exception = YpRequiredPropertyException.class) String phoneNumberEndpoint)
            throws YpRequiredPropertyException {
        AuthProperties.ENDPOINT_GET_PHONE_NUMBER.set(this, phoneNumberEndpoint);
    }


    class AuthProperties {
        private static final String PREFIX = propertyPath(YpSdkConfiguration.GLOBAL_PREFIX, "auth");

        private static final String SERVER_BASE_URL_KEY = propertyPath(PREFIX, "base-url");
        public static final YpStringProperty SERVER_BASE_URL = new YpStringProperty(
                propertyPath(PREFIX, SERVER_BASE_URL_KEY),
                true,
                "https://prod.yottapay.co.uk/launcher"
        );

        public static final String CREDENTIALS_USERNAME_KEY = propertyPath(PREFIX, "credentials.username");
        public static final YpStringProperty CREDENTIALS_USERNAME = new YpStringProperty(
                CREDENTIALS_USERNAME_KEY,
                true
        );

        private static final String CREDENTIALS_PASSWORD_KEY = propertyPath(PREFIX, "credentials.password");
        public static final YpStringProperty CREDENTIALS_PASSWORD = new YpStringProperty(
                CREDENTIALS_PASSWORD_KEY,
                true
        );

        private static final String ENDPOINT_CHECK_CONSENT_KEY = propertyPath(PREFIX, "endpoints.check-consent");
        public static final YpStringProperty ENDPOINT_CHECK_CONSENT = new YpStringProperty(
                ENDPOINT_CHECK_CONSENT_KEY,
                true,
                "/external/authgate/consent-status"
        );

        private static final String ENDPOINT_CREATE_AUTH_KEY = propertyPath(PREFIX, "endpoints.create-auth");
        public static final YpStringProperty ENDPOINT_CREATE_AUTH = new YpStringProperty(
                ENDPOINT_CREATE_AUTH_KEY,
                true,
                "/external/authgate/auth-new"
        );

        private static final String ENDPOINT_EXCHANGE_TOKEN_KEY = propertyPath(PREFIX, "endpoints.exchange-token");
        public static final YpStringProperty ENDPOINT_EXCHANGE_TOKEN = new YpStringProperty(
                ENDPOINT_EXCHANGE_TOKEN_KEY,
                true,
                "/external/authgate/data/user/exchange"
        );

        private static final String ENDPOINT_GET_DELIVERY_ADDRESS_KEY = propertyPath(PREFIX, "endpoints.get-delivery-address");
        public static final YpStringProperty ENDPOINT_GET_DELIVERY_ADDRESS = new YpStringProperty(
                ENDPOINT_GET_DELIVERY_ADDRESS_KEY,
                true,
                "/external/authgate/data/user/get-delivery-addr"
        );

        private static final String ENDPOINT_ADD_MERCHANT_KEYS_KEY = propertyPath(PREFIX, "endpoints.add-merchant-keys");
        public static final YpStringProperty ENDPOINT_ADD_MERCHANT_KEYS = new YpStringProperty(
                ENDPOINT_ADD_MERCHANT_KEYS_KEY,
                true,
                "/external/authgate/data/user/add-merchant-token"
        );

        private static final String ENDPOINT_GET_EMAIL_KEY = propertyPath(PREFIX, "endpoints.get-email");
        public static final YpStringProperty ENDPOINT_GET_EMAIL = new YpStringProperty(
                ENDPOINT_GET_EMAIL_KEY,
                true,
                "/external/authgate/data/user/get-real-email"
        );

        private static final String ENDPOINT_GET_FULL_NAME_KEY = propertyPath(PREFIX, "endpoints.get-full-name");
        public static final YpStringProperty ENDPOINT_GET_FULL_NAME = new YpStringProperty(
                ENDPOINT_GET_FULL_NAME_KEY,
                true,
                "/external/authgate/data/user/get-user-name"
        );

        private static final String ENDPOINT_GET_PHONE_NUMBER_KEY = propertyPath(PREFIX, "endpoints.get-phone-number");
        public static final YpStringProperty ENDPOINT_GET_PHONE_NUMBER = new YpStringProperty(
                ENDPOINT_GET_PHONE_NUMBER_KEY,
                true,
                "/external/authgate/data/user/get-phone-number"
        );
    }
}
