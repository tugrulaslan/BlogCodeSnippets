package com.tugrulaslan.util;

import java.util.UUID;

public final class IntegrationUtils {
    private IntegrationUtils() {
    }

    public static String buildClientId(String eventName, String type) {
        return String.format("%s_Event_%s_%s", eventName, type, buildRandomUUID());
    }

    private static String buildRandomUUID() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "_");
    }
}