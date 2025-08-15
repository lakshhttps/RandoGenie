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

class DiceRoll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dice_roll)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val image = mapOf(
            1 to R.drawable.dice_1,
            2 to R.drawable.dice_2,
            3 to R.drawable.dice_3,
            4 to R.drawable.dice_4,
            5 to R.drawable.dice_5,
            6 to R.drawable.dice_6
        )

        val rollbtn = findViewById<Button>(R.id.rollbtn)
        val resultbox = findViewById<TextView>(R.id.result)
        val diceImage = findViewById<ImageView>(R.id.diceImage)

        rollbtn.setOnClickListener {
            val result = Random.nextInt(1, 7)
            val resultimage = image.getValue(result)

            diceImage.animate()
                .rotationBy(1800f)
                .setDuration(1500)
                .withEndAction {
                    diceImage.setImageResource(resultimage)
                    diceImage.rotation = 0f
                    resultbox.text = "Result: $result"
                }
                .start()
        }
    }
}