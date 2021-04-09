package com.example.cleangallery.data.API;

import com.example.cleangallery.data.models.RetrofitModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("photos")
    Observable<List<RetrofitModel>> getAllData();
}
