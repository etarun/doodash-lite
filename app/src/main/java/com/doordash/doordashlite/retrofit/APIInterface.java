package com.doordash.doordashlite.retrofit;

import com.doordash.doordashlite.data.LoginData;
import com.doordash.doordashlite.data.Restaurant;
import com.doordash.doordashlite.data.TokenResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface APIInterface {

    @POST("b/ss/baamdev/6/")
    Observable<List<Restaurant>> getAllRestaurants(@QueryMap Map<String, String> queryMap);

    @GET("/v2/restaurant/{id}")
    Observable<Restaurant> getRestaurantData(@Path(value = "id", encoded = true) int id);

    @POST("/v2/auth/token/")
    Observable<TokenResponse> getTokenData(@Body LoginData loginData);

}
