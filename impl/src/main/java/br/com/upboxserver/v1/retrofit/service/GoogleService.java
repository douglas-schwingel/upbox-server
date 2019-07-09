package br.com.upboxserver.v1.retrofit.service;

import br.com.upboxserver.v1.retrofit.client.Resposta;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GoogleService {

    @POST("siteverify")
    Call<Resposta> enviaToken(@Query("secret") String secret, @Query("response") String response);
}
