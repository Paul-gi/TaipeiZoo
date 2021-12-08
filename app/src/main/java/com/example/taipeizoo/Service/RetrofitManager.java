package com.example.taipeizoo.Service;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    //Singleton
    private static volatile RetrofitManager mRetrofitManager;

    public RetrofitManager() {
        Retrofit mRetrofit = retrofit();
    }


    public <T> T createService(final Class<T> service) {
        return retrofit().create(service);
    }

    public static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (Retrofit.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://data.taipei")  //url = baseurl + @GER("...")
                .addConverterFactory(GsonConverterFactory.create())
                .client(Objects.requireNonNull(okHttpClient()))
                .build();


    }


    private OkHttpClient okHttpClient() {
        try {
            return new OkHttpClient.Builder()
                    .addNetworkInterceptor(chain -> chain.proceed(chain.request()))
                    .retryOnConnectionFailure(true)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .build();

        } catch (Exception e) {
            Log.v("OkhttpException", "Exception" + e);
        }
        return null;
    }


}
