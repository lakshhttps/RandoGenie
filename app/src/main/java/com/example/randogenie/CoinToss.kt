package com.example.randogenie

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class CoinToss : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coin_toss)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tossbtn = findViewById<Button>(R.id.tossbtn)
        val resultbox = findViewById<TextView>(R.id.result)
        val coinImage = findViewById<ImageView>(R.id.coinImage)
        tossbtn.setOnClickListener {
            val result = if(Random.nextBoolean()) "Heads" else "Tails"
            val resultimage = if (result == "Heads") R.drawable.coin_heads else R.drawable.coin_tails

            coinImage.animate()
                .rotationXBy(1800f)
                .setDuration(1500)
                .withEndAction {
                    coinImage.setImageResource(resultimage)
                    coinImage.rotationX = 0f
                    resultbox.text = "Result: $result"
                }
                .start()

        }
    }
}