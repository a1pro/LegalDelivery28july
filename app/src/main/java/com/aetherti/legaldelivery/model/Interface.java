package com.aetherti.legaldelivery.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {

    @GET("00000000-2d76-4153-ffff-ffffe5fa3e73")
    Call<BlueScreenStatusModel> isBlueScreen();
}
