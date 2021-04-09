package com.example.cleangallery.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.cleangallery.R;
import com.example.cleangallery.data.models.RetrofitModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHodler> {

    List<RetrofitModel> models = new ArrayList<>();
    Context context;
    @NonNull
    @NotNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_view , parent , false);
        MyViewHodler viewHodler = new MyViewHodler(view);

        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHodler holder, int position) {
        RetrofitModel retrofitModel = models.get(position);

        GlideUrl url = new GlideUrl(retrofitModel.getUrl() , new LazyHeaders.Builder()
                .addHeader("User-Agent", "app_agent")
                .build());
        Glide.with(context).load(url).into(holder.picture);

        holder.title.setText("Title : " + retrofitModel.getTitle());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (holder.picture.getDrawable() == null){
                    holder.bar.setVisibility(View.VISIBLE);
                }else {
                    holder.bar.setVisibility(View.GONE);
                }
            }
        } , 1000);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        ImageView picture;
        TextView title;
        ProgressBar bar;
        public MyViewHodler(@NonNull @NotNull View itemView) {
            super(itemView);

            bar = itemView.findViewById(R.id.progressBar);
            picture = itemView.findViewById(R.id.picture);
            title = itemView.findViewById(R.id.pic_title);
        }
    }

    public List<RetrofitModel> getModels() {
        return models;
    }

    public void setModels(List<RetrofitModel> models) {
        this.models = models;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
