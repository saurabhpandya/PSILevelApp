package com.psilevelapp.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.psilevelapp.constants.Constants.BASE_URL;

public class RestClient {

    private static final String TAG = RestClient.class.getSimpleName();

    public RestClient() { getClient(); }

    public static Retrofit getClient() {
        return RetrofitAPI.getRetrofit();
    }

    private static Retrofit getClientInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    private static class RetrofitAPI {

        static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new ConnectivityInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        private static Retrofit retrofit = getClientInstance(okHttpClient);

        private static Retrofit getRetrofit() {
            if (retrofit == null)
                retrofit = getClientInstance(okHttpClient);
            return retrofit;
        }

    }

}
