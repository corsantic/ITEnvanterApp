package com.example.hsarkisla.itenvantermobilapp.Services;


import com.example.hsarkisla.itenvantermobilapp.Model.Envanter;
import com.example.hsarkisla.itenvantermobilapp.Model.Kategori;
import com.example.hsarkisla.itenvantermobilapp.Model.Lokasyon;
import com.example.hsarkisla.itenvantermobilapp.Model.Personel;
import com.example.hsarkisla.itenvantermobilapp.Model.Urun;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import retrofit2.http.Path;


/**
 * Created by pc on 26.07.2017.
 */

public interface APIService {
    //GET by id
    @GET("api/Default/GetUrun/{barcode}")
    Call<List<Urun>> getUrun(@Path("barcode") String barcode);
    //Get Location by id
    @GET("api/Default/GetLokasyon/{id}")
    @Headers("Content-type: application/json")
    Call<List<Lokasyon>> getLocation(@Path("id") int id);
    //Get all Location
    @GET("api/Default/GetTumLokasyon")
    Call<List<Lokasyon>>  getAllLocation();
    //Get Personel by id
    @GET("api/Default/GetPersonel/{id}")
    @Headers("Content-type: application/json")
    Call<List<Personel>> getPersonel(@Path("id") int id);

    @GET("api/Default/GetKategoriListele")
    Call<List<Kategori>>  getAllKategori();
    //GET by Barcode

    //ADD New Envanter

    //ADD New Urun
    @POST("api/Default/SetYeniUrun/{Urun}")
    @Headers("Content-type: application/json")
    Call<Urun> addUrun(@Body Urun urun);

    /***
     *todo :Envanter Kayıt
     * @POST("api/Default/UpdateEnvanter/{Envanter}")
     @Headers("Content-type: application/json")
     Call<Envanter> addEnvanter(@Body Envanter envanter);*/
}
