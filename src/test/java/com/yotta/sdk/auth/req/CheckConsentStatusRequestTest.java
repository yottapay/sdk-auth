package com.yotta.sdk.auth.req;

import com.yotta.sdk.auth.YpSdkAuth;
import com.yotta.sdk.auth.config.YpSdkAuthConfiguration;
import com.yotta.sdk.auth.domain.YpAuthConsentStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.Times;
import org.mockserver.model.StringBody;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class CheckConsentStatusRequestTest {

    private static final int SERVER_PORT = 8081;
    private static final String SERVER_BASE_URL = "http://127.0.0.1:" + SERVER_PORT;

    private static final String ENDPOINT_CHECK_CONSENT = "/check-consent";
    private static final String REQUEST_METHOD = "GET";

    private static final String TOKEN_REQUESTED_VALUE = "user-consent-requested";
    private static final String TOKEN_GRANTED_VALUE = "user-consent-granted";
    private static final String TOKEN_NOT_GRANTED_VALUE = "user-consent-not-granted";
    private static final String TOKEN_NOT_FOUND_VALUE = "user-consent-not-found";


    private static ClientAndServer mockServer;
    private static YpSdkAuth sdk;

    @BeforeClass
    public static void startServer() {
        mockServer = ClientAndServer.startClientAndServer(SERVER_PORT);
        mockServer.when(request()
                        .withPath(ENDPOINT_CHECK_CONSENT)
                        .withMethod(REQUEST_METHOD)
                        .withHeader(YpAbstractAuthHttpRequest.HEADER_USER_CONSENT_NAME, TOKEN_REQUESTED_VALUE),
                Times.once())
                .respond(response()
                        .withStatusCode(200)
                        .withBody(StringBody.exact("{\"extConsentState\":\"REQUESTED\"}")));
        mockServer.when(request()
                        .withPath(ENDPOINT_CHECK_CONSENT)
                        .withMethod(REQUEST_METHOD)
                        .withHeader(YpAbstractAuthHttpRequest.HEADER_USER_CONSENT_NAME, TOKEN_GRANTED_VALUE),
                Times.once())
                .respond(response()
                        .withStatusCode(200)
                        .withBody(StringBody.exact("{\"extConsentState\":\"GRANTED\"}")));
        mockServer.when(request()
                        .withPath(ENDPOINT_CHECK_CONSENT)
                        .withMethod(REQUEST_METHOD)
                        .withHeader(YpAbstractAuthHttpRequest.HEADER_USER_CONSENT_NAME, TOKEN_NOT_GRANTED_VALUE),
                Times.once())
                .respond(response()
                        .withStatusCode(200)
                        .withBody(StringBody.exact("{\"extConsentState\":\"NOT_GRANTED\"}")));
        mockServer.when(request()
                        .withPath(ENDPOINT_CHECK_CONSENT)
                        .withMethod(REQUEST_METHOD)
                        .withHeader(YpAbstractAuthHttpRequest.HEADER_USER_CONSENT_NAME, TOKEN_NOT_FOUND_VALUE),
                Times.once())
                .respond(response()
                        .withStatusCode(404)
                        .withBody(""));
    }

    @BeforeClass
    public static void configureSdk() {
        YpSdkAuthConfiguration config = YpSdkAuthConfiguration.createDefault();
        config.setUsername("username");
        config.setPassword("password");
        config.setServerBaseUrl(SERVER_BASE_URL);
        config.setCheckConsentEndpoint(ENDPOINT_CHECK_CONSENT);

        sdk = YpSdkAuth.createFromConfiguration(config);
    }

    @AfterClass
    public static void stopServer() {
        mockServer.stop();
    }

    @Test
    public void test_checkConsentRequestGranted() {
        Assert.assertEquals(YpAuthConsentStatus.GRANTED, sdk.checkConsentStatus(TOKEN_GRANTED_VALUE));
    }

    @Test
    public void test_checkConsentRequestRequested() {
        Assert.assertEquals(YpAuthConsentStatus.REQUESTED, sdk.checkConsentStatus(TOKEN_REQUESTED_VALUE));
    }

    @Test
    public void test_checkConsentRequestNotGranted() {
        Assert.assertEquals(YpAuthConsentStatus.NOT_GRANTED, sdk.checkConsentStatus(TOKEN_NOT_GRANTED_VALUE));
    }

    @Test
    public void test_checkConsentNotFound() {
        Assert.assertEquals(YpAuthConsentStatus.NOT_FOUND, sdk.checkConsentStatus(TOKEN_NOT_FOUND_VALUE));
    }
}
