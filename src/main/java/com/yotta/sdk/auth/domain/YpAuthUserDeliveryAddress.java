package com.yotta.sdk.auth.domain;

import lombok.Data;

@Data
public class YpAuthUserDeliveryAddress {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postcode;
    private String country;
}
