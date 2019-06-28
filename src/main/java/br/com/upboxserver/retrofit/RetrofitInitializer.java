package br.com.upboxserver.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    private String baseUrl;
    private Retrofit retrofit;
    private Tipo tipo;

    public RetrofitInitializer(String url, Tipo tipo) {
        baseUrl = url;
        this.tipo = tipo;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);

        Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofitGoogle(client);
    }

    private void retrofitFtp(Builder client) {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .client(client.build()).build();
    }

    private void retrofitGoogle(Builder client) {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(client.build()).build();
    }

    public GoogleService getGoogleService() {
        return retrofit.create(GoogleService.class);
    }

}
