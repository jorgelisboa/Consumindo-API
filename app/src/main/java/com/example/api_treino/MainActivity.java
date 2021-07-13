package com.example.api_treino;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnAction;
    private EditText etCep;
    private List<CEP> ceps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        etCep = findViewById(R.id.etCep);
        btnAction = findViewById(R.id.btnAction);
    }

    public void useApi(View view) {
        Tarefa tarefa = new Tarefa();
        tarefa.execute("https://viacep.com.br/ws/" + etCep.getText().toString() + "/json/");
    }


    private class Tarefa extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String retorno = ApiConection.getData(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            ceps = ConsumeJson.jsonDados(s);
            exibirDados();
        }

        private void exibirDados() {
            if (ceps != null){
                for(CEP cep : ceps){
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("cep", cep);
                    startActivity(intent);
                }
            }
        }
    }
}