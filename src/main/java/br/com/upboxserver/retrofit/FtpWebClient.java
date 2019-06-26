package br.com.upboxserver.retrofit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import retrofit2.Call;

import java.io.IOException;

public class FtpWebClient {
    private static final Logger logger = LoggerFactory.getLogger(FtpWebClient.class);
    private static final Marker marker = MarkerFactory.getMarker("ftpWebClient");

    public boolean enviaUsuario(String username, String password) throws IOException {
        logger.info(marker, "Enviando usuario {} para o FTP", username);
        Call<Resposta> call = new RetrofitInitializer("http://localhost:9001", Tipo.FTP).getFtpWebService().criaFtpClient(username, password);
        return call.execute().body().isSuccess();
    }
}
