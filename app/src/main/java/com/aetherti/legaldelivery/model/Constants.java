package com.aetherti.legaldelivery.model;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class Constants {
    public static OkHttpClient getNewHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(null)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS);
        return client.build();
    }
}
