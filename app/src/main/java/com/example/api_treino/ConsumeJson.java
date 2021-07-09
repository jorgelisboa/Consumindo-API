package com.example.api_treino;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ConsumeJson {
    public static List<CEP> jsonDados(String conteudo) {
        try {
            List<CEP> cepList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(conteudo);

                CEP cep = new CEP();
                cep.setCep(jsonObject.getString("cep"));
                cep.setLogradouro(jsonObject.getString("logradouro"));
                cep.setComplemento(jsonObject.getString("complemento"));
                cep.setBairro(jsonObject.getString("bairro"));
                cep.setLocalidade(jsonObject.getString("localidade"));
                cep.setUf(jsonObject.getString("uf"));
                cep.setDdd(jsonObject.getString("ddd"));
                cep.setGia(jsonObject.getString("gia"));
                cep.setIbge(jsonObject.getString("ibge"));
                cep.setSiafi(jsonObject.getString("siafi"));

                cepList.add(cep);
            return cepList;
        } catch (Exception e){
            e.printStackTrace();
            Log.d("test", "ERRO");
            return null;
        }
    }
}
