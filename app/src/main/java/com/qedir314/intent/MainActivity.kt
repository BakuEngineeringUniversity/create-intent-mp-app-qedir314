package com.qedir314.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.media.MediaPlayer
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val locationText: TextView = findViewById(R.id.locationText)
        locationText.setOnClickListener {

            val geoUri = "geo:0,0?q=Pasadena, California"

            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))

            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            }
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            mediaPlayer?.start()
        }

        val callButton: Button = findViewById(R.id.callButton)

        callButton.setOnClickListener {
            val phoneNumber = "tel:1234567890"
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse(phoneNumber)
            startActivity(callIntent)
        }

        val emailButton: Button = findViewById(R.id.emailButton)


        emailButton.setOnClickListener {
            val recipient = "example@example.com"
            val subject = "Hello!"
            val body = "This is a test email from my app."

            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "message/rfc822"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, body)

            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}