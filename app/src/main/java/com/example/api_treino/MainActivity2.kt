package com.example.api_treino

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

//"val" é constante, "var" é variavel
    const val CHANNEL_ID = "channelID"
    const val CHANNEL_NAME = "channelName"
    const val NOTIFICATION_ID = 0

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Objects.requireNonNull(supportActionBar)!!.hide()

        createNotification()
        val cep = intent.extras?.getParcelable<CEP>("cep")
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Encomenda enviada.")
            .setContentText("Enviamos seu pedido para " + cep?.getBairro()!!)
            .setSmallIcon(R.drawable.clock)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID, notification)

        var txtFinalizar = findViewById<TextView>(R.id.txtFinalizado)
        if (cep != null) {
            txtFinalizar.text =
                "Muito obrigado pela preferência, acompanhe o processo de envio de sua encomenda pelo app. Enviamos seu pedido para " + cep.getLocalidade() + " no endereço: " + cep.getBairro() + " em " + cep.getLogradouro()
        } else {
            txtFinalizar.text = "Seu CEP não foi encontrado, desculpe pelo inconveniente."
        }
    }

    //Métodos são "fun"
    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
               lightColor = Color.GREEN
               enableLights(true)
           }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun onVoltar(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}