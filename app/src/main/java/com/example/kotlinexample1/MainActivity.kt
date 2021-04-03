package com.example.kotlinexample1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlinexample1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // private
    // var -> change the value later
    // val -> once u declare, you cannot change the value
    private lateinit var buttonGo : Button // telling a variable called buttonGo and it's a button type
    private lateinit var editTextName : EditText
    private lateinit var binding : ActivityMainBinding
    private lateinit var buttonVillager : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonGo = binding.buttonGo
        editTextName = binding.editTextName
        buttonVillager = binding.buttonVillagers

//        setContentView(R.layout.activity_main)
//
//        buttonGo = findViewById(R.id.button_go);
//        editTextName = findViewById(R.id.editText_name);

        buttonGo.setOnClickListener{
//            toastInput(editTextName.text.toString());
            launchSecondActivity(editTextName.text.toString())
        }
        buttonVillager.setOnClickListener{
            launchThirdActivity()
        }
    }

    private fun toastInput(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private fun launchSecondActivity(message : String) {
        val intent = Intent(this, SecondActivity:: class.java) // this is the standard way to refer to the class that you're linking to
        intent.putExtra("name", message)
        startActivity(intent)
    }

    private fun launchThirdActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
    }
}