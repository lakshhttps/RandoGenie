package com.example.randogenie

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val coinbtn = findViewById<MaterialCardView>(R.id.coin)
        val dicebtn = findViewById<MaterialCardView>(R.id.dice)
        val passwordbtn = findViewById< MaterialCardView>(R.id.password)
        val listbtn = findViewById<MaterialCardView>(R.id.list)

        coinbtn.setOnClickListener {
            intent = Intent(applicationContext, CoinToss::class.java)
            startActivity(intent)
        }

        dicebtn.setOnClickListener {
            intent = Intent(applicationContext, DiceRoll::class.java)
            startActivity(intent)
        }

        passwordbtn.setOnClickListener {
            intent = Intent(applicationContext, PasswordGenerator::class.java)
            startActivity(intent)
        }

        listbtn.setOnClickListener {
            intent = Intent(applicationContext, CustomList::class.java)
            startActivity(intent)
        }

    }
}