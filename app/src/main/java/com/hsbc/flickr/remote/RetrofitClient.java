package com.hsbc.flickr.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://api.flickr.com/services/feeds/";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
            httpClient.addInterceptor(logging);  //
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BASE_URL);
            Gson gson = getGsonFactory();

            builder.addConverterFactory(GsonConverterFactory.create(gson));
            builder.client(httpClient.build());
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

           retrofit = builder.build();
        }
        return retrofit;
    }

    @NonNull
    private static Gson getGsonFactory() {
        return new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create();
    }
}
