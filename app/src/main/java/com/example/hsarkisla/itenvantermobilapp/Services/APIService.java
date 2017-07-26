package com.example.hsarkisla.itenvantermobilapp.Services;



        import com.example.hsarkisla.itenvantermobilapp.Model.Urun;

import java.util.List;

import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;


/**
 * Created by pc on 26.07.2017.
 */

public interface APIService {
    @GET("api/Default/GetUrun/{id}")
    Call<List<Urun>> getUrun(@Path("id") int id);


}
