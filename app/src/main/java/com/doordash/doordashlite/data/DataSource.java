package com.doordash.doordashlite.data;


import com.doordash.doordashlite.retrofit.APIInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;


public class DataSource {

    public static final String QUERY_PARAM_LAT = "lat";
    public static final String QUERY_PARAM_LNG = "lng";
    public static final String QUERY_OFFSET = "offset";
    public static final String QUERY_LIMIT = "limit";

    private APIInterface apiInterface;

    public DataSource(APIInterface apiInterface) {
        this.apiInterface = apiInterface;

    }

    Observable<List<Restaurant>> getRestaurants(int start, int end) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_PARAM_LAT, "37.422740");
        queryMap.put(QUERY_PARAM_LNG, "-122.139956");
        queryMap.put(QUERY_OFFSET, String.valueOf(start));
        queryMap.put(QUERY_LIMIT, String.valueOf(end));

        return apiInterface.getAllRestaurants(queryMap)
                .flatMap(Observable::just);
    }

    Observable<Restaurant> getRestaurantData(int restaurantId) {

        return apiInterface.getRestaurantData(restaurantId)
                .flatMap(Observable::just);
    }


    public Observable<TokenResponse> getTokenData(String username, String password) {

        LoginData loginData = new LoginData(username, password);
        return apiInterface.getTokenData(loginData);
    }
}
