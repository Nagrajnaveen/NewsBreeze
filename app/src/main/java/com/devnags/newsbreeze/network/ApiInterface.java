package com.devnags.newsbreeze.network;

import com.devnags.newsbreeze.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {




    @GET("v2/everything?q=apple&from=2021-06-19&to=2021-06-19&sortBy=popularity&apiKey=173b641e010848a39a48262588c0f532")
    Call<ResponseData> getData();



}
