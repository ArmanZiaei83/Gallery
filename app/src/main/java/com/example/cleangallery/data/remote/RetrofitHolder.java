package com.example.cleangallery.data.remote;

import com.example.cleangallery.data.API.Api;
import com.example.cleangallery.data.OnEventHandler;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHolder implements OnEventHandler {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Api getApi(){
        Api api = retrofit.create(Api.class);
        onSuccess("Api.class Prepared For Retrofit");

        if (api == null){
            onError("Error : Api.class Returned a null object");
        }
        return api;
    }

    @Override
    public void onSuccess(String message) {
        makeSout(message);
    }

    private void makeSout(String message) {
        System.out.println(message);
    }

    @Override
    public void onError(String error) {
        makeSout(error);
    }

    @Inject
    public RetrofitHolder() {
    }
}
