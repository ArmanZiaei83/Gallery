package com.example.cleangallery.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cleangallery.R;
import com.example.cleangallery.data.models.RetrofitModel;
import com.example.cleangallery.di.DaggerDataComponent;
import com.example.cleangallery.di.DataComponent;
import com.example.cleangallery.domain.DataAccessor;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    //UI:
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RecyclerAdapter adapter = new RecyclerAdapter();

    DataComponent component = DaggerDataComponent.create();
    @Inject
    DataAccessor accessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        adapter.setContext(this);
        subscriber();

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recylerView);
        manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
    }
    public Observable<RetrofitModel> getDataFromDataAccessor(){
        component.inject(this);
        return accessor.getAllData().flatMap(new Function<List<RetrofitModel>, ObservableSource<RetrofitModel>>() {
            @Override
            public ObservableSource<RetrofitModel> apply(@NotNull List<RetrofitModel> retrofitModels) throws Exception {
                adapter.setModels(retrofitModels);
                recyclerView.setAdapter(adapter);
                return Observable.fromIterable(retrofitModels).subscribeOn(Schedulers.io());
            }
        });
    }

    public void subscriber(){
        getDataFromDataAccessor().subscribe(new Observer<RetrofitModel>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull RetrofitModel retrofitModels) {
                System.out.println(retrofitModels.getUrl());
            }

            @Override
            public void onError(@NotNull Throwable e) {
                System.out.println("Error : " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

}