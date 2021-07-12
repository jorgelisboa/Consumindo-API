package com.example.api_treino

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat

//"val" é constante, "var" é variavel
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        createNotification()

        var cep: CEP? = intent.getParcelableExtra<CEP>("cep")
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Encomenda enviada.")
                .setContentText("")
    }

    //Métodos são "fun"
    fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
               lightColor = Color.GREEN
               enableLights(true)
           }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}