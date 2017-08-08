package com.example.hsarkisla.itenvantermobilapp.Services;

import com.example.hsarkisla.itenvantermobilapp.Model.Envanter;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by pc on 8.08.2017.
 */

public interface EnvanterService {



    //ADD New Urun
    @POST("api/Default/UpdateEnvanter/{Envanter}")
    @Headers("Content-type: application/json")
    Call<Envanter> addEnvanter(@Body Envanter envanter);

}
