package com.example.api_treino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConection {
    //Le minha url

    public static String getData(String uri) {
        BufferedReader bufferedReader = null;
        try {
            //URL class direct to a Location on the web
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));//Pega dados existententes da api

            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha+"\n");
            }
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (bufferedReader != null) {
                try{
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
