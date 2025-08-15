package com.example.randogenie

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class PasswordGenerator : AppCompatActivity() {
    private var copypass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password_generator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val generatebtn = findViewById<Button>(R.id.generatebtn)
        val resultbox = findViewById<TextView>(R.id.result)
        val lengthbox = findViewById<TextInputEditText>(R.id.lengthbox)
        val copybtn = findViewById<Button>(R.id.copybtn)

        generatebtn.setOnClickListener {
            val lengthstring = lengthbox.text.toString()
            val length = lengthstring.toIntOrNull()

            if (length == null || length <= 0) {
                Toast.makeText(this, "Enter a valid password length", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()-_=+"
            val finalPassword = (1..length).map { chars.random() }.joinToString("")
            copypass = finalPassword

            val scrambleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            val duration = 1000L
            val interval = 50L
            val endTime = System.currentTimeMillis() + duration
            val handler = Handler(Looper.getMainLooper())
            val runnable = object : Runnable {
                override fun run() {
                    if (System.currentTimeMillis() < endTime) {
                        val randomString = (1..length).map { scrambleChars.random() }.joinToString("")
                        resultbox.text = "Password: $randomString"
                        handler.postDelayed(this, interval)
                    } else {
                        resultbox.alpha = 0f
                        resultbox.scaleX = 0.5f
                        resultbox.scaleY = 0.5f
                        resultbox.text = "Password: $finalPassword"
                        resultbox.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(500).start()
                    }
                }
            }
            handler.post(runnable)
        }

        copybtn.setOnClickListener {
            if (copypass.isNotEmpty()) {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Password", copypass)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Password copied to clipboard", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No password to copy", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
