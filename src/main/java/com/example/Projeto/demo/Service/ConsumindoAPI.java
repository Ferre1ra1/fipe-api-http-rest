package com.example.Projeto.demo.Service;

import java.net.URI;
import java.net.http.*;
import java.io.IOException;
public class ConsumindoAPI {

    public String getDados(String endereco){

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisao = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
                HttpResponse<String> resposta = null;
            try{
                resposta = cliente .send(requisao, HttpResponse.BodyHandlers.ofString());
            }catch(IOException | InterruptedException e){
                throw new RuntimeException();
            }

        String json = resposta.body();
        return json;
    }

}
