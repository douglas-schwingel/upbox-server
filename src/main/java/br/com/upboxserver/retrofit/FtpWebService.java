package br.com.upboxserver.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FtpWebService {

    @FormUrlEncoded
    @POST("/cria_usuario")
    Call<Resposta> criaFtpClient(@Field("username") String username, @Field("password") String password);
}
