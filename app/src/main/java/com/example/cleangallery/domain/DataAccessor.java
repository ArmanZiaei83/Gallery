package com.example.cleangallery.domain;

import com.example.cleangallery.data.models.RetrofitModel;
import com.example.cleangallery.data.remote.RetrofitHolder;
import com.example.cleangallery.di.DaggerDataComponent;
import com.example.cleangallery.di.DataComponent;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableRange;
import io.reactivex.schedulers.Schedulers;

public class DataAccessor {

    DataComponent dataComponent = DaggerDataComponent.create();
    @Inject
    RetrofitHolder retrofitHolder;

    public Observable<List<RetrofitModel>> getAllData(){
        dataComponent.inject(this);

        return retrofitHolder.getApi()
        .getAllData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }

    @Inject
    public DataAccessor() {
    }
}
