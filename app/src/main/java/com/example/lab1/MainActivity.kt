package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var field: EditText
    private lateinit var button: Button

    private var number: Int = (1..100).random()
    private var isGameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.textView)
        field = findViewById(R.id.editTextNumberDecimal)
        button = findViewById(R.id.button)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onClick(view: View) {
        if (isGameOver) {
            isGameOver = false
            number = (1..100).random()
            text.setText(R.string.start)
            button.setText(R.string.try_to_guess)
            field.isEnabled = true
            field.text.clear()
        } else {
            val entered = field.text.toString()
            field.text.clear()
            val enteredNumber = try {
                entered.toInt()
            } catch (e: NumberFormatException) {
                text.setText(R.string.error)
                return
            }
            if (!(1..100).contains(enteredNumber)) {
                text.setText(R.string.start)
                return
            }

            if (enteredNumber < number) {
                text.setText(R.string.behind)
            } else if (enteredNumber > number) {
                text.setText(R.string.ahead)
            } else {
                text.setText(R.string.hit)
                isGameOver = true
                button.setText(R.string.play_more)
                field.isEnabled = false
            }
        }
    }
}