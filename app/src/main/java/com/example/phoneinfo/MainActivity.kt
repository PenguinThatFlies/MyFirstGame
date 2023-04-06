package com.example.phoneinfo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.phoneinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        loadData()

        bindingClass.btbutton.setOnClickListener{
            saveData()
        }
    }

    private fun saveData() {
        val insertedText = bindingClass.ettext.text.toString()
        bindingClass.tvtext.text = insertedText

        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putString("STRING_KEY", insertedText)
        }.apply()
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)

        bindingClass.tvtext.text = savedString
    }
}