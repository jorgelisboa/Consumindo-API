package com.example.api_treino;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnAction;
    private EditText etCep;
    private List<CEP> ceps = new ArrayList<>();
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

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