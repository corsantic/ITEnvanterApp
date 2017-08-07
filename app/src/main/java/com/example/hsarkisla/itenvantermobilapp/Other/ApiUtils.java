package com.example.hsarkisla.itenvantermobilapp.Other;

import com.example.hsarkisla.itenvantermobilapp.Services.APIService;

/**
 * Created by pc on 1.08.2017.
 */


public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://193.1.1.5/itenvanterapi/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}