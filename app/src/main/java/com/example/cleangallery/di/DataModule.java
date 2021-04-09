package com.example.cleangallery.di;

import com.example.cleangallery.data.remote.RetrofitHolder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Singleton
    @Provides
    RetrofitHolder retrofitHolder(){
        return new RetrofitHolder();
    }
}
