package com.yotta.sdk.auth.config;

import com.yotta.sdk.core.config.YpSdkAbstractConfiguration;
import org.jetbrains.annotations.Nullable;

public class YpSdkAuthConfigurationImpl extends YpSdkAbstractConfiguration implements YpSdkAuthConfiguration {

    @Override
    @Nullable
    protected Class<?>[] getPropertiesUtilityClasses() {
        return new Class[]{YpSdkAuthConfiguration.AuthProperties.class};
    }
}
