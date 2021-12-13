package com.example.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnExplict = findViewById<Button>(R.id.btnExplicit)
        var btnImplict = findViewById<Button>(R.id.btnImplicit)
        var btnEmail = findViewById<Button>(R.id.btnEmail)
        var btnCapture = findViewById<Button>(R.id.btnCapture)


        val context = this
        btnExplict.setOnClickListener{
            val intent = Intent(context,MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        btnImplict.setOnClickListener{
            var intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Code Android")
            intent.type = "text/plain"
            startActivity(intent)
        }
        btnCapture.setOnClickListener{
            capturePhoto()
        }



        btnEmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf("abdullahcui77@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Complain")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }



    }
    fun capturePhoto() {
        val intent = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
        if (intent.resolveActivity(packageManager) != null) {
            val REQUEST_IMAGE_CAPTURE =1
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

}