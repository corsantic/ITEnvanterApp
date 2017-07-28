package com.example.hsarkisla.itenvantermobilapp.Services;



        import com.example.hsarkisla.itenvantermobilapp.Model.Urun;

import java.util.List;

import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.PUT;
        import retrofit2.http.Path;


/**
 * Created by pc on 26.07.2017.
 */

public interface APIService {
    //GET by id
    @GET("api/Default/GetUrun/{id}")
    Call<List<Urun>> getUrun(@Path("id") int id);
    //GET by Barcode
    @GET("api/Default/GetUrun/{BarcodeNo}")
    Call<List<Urun>> getUrunBarcode(@Path("BarcodeNo") String barcode);
   //ADD New Urun
    @POST("api/Default/SetYeniUrun/{Urun}")
    Call<Urun> addUrun(@Body Urun urun);




}
