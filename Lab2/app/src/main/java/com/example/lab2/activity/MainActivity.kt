package com.example.lab2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.R
import com.example.lab2.fragment.DogsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, DogsFragment())
            .commit()
    }
}
