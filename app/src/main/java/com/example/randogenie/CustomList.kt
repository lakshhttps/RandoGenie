package com.example.randogenie

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class CustomList : AppCompatActivity() {

    private val choices = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputField = findViewById<TextInputEditText>(R.id.inputField)
        val addBtn = findViewById<Button>(R.id.btnAdd)
        val chooseBtn = findViewById<Button>(R.id.choose)
        val resultText = findViewById<TextView>(R.id.resultText)
        val listView = findViewById<ListView>(R.id.listView)

        adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            choices
        ) {
            override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.setTextColor(android.graphics.Color.BLACK) // Force text color to black
                return view
            }
        }
        listView.adapter = adapter

        addBtn.setOnClickListener {
            val text = inputField.text.toString().trim()
            if (text.isNotEmpty()) {
                choices.add(text)
                adapter.notifyDataSetChanged()
                inputField.text?.clear()
                Toast.makeText(this, "$text added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Enter a valid item", Toast.LENGTH_SHORT).show()
            }
        }

        chooseBtn.setOnClickListener {
            if (choices.isNotEmpty()) {
                val winner = choices[Random.nextInt(choices.size)]
                resultText.rotationY = 90f
                resultText.text = "Winner: $winner"


                resultText.animate()
                    .rotationY(0f)
                    .setDuration(1000)
                    .start()

            } else {
                Toast.makeText(this, "No items to choose from", Toast.LENGTH_SHORT).show()
            }
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val removedItem = choices.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "$removedItem removed", Toast.LENGTH_SHORT).show()
            true
        }
    }
}
