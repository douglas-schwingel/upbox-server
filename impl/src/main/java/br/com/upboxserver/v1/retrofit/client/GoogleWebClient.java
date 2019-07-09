package br.com.upboxserver.v1.retrofit.client;

import br.com.upboxserver.v1.retrofit.config.RetrofitInitializer;
import org.springframework.stereotype.Component;
import retrofit2.Call;

import java.io.IOException;

@Component
public class GoogleWebClient {

    private static final String SECRET = "6LcsQqoUAAAAAOUsBLwlQWKiRvZyHXLuJLIWpNqP";

    public boolean verifica(String recaptcha) throws IOException {
        Call<Resposta> token = new RetrofitInitializer("https://www.google.com/recaptcha/api/").getGoogleService().enviaToken(SECRET, recaptcha);
        return token.execute().body().isSuccess();
    }
}
