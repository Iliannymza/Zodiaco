package com.example.zodiaco.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zodiaco.R
import com.example.zodiaco.data.Horoscopo
import com.example.zodiaco.data.HoroscopoAdapter
import com.example.zodiaco.data.HoroscopoProvider

class MainActivity : AppCompatActivity() {

    var horoscopoLista = HoroscopoProvider.getAll()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopoAdapter(horoscopoLista, { position ->
            val horoscopo = horoscopoLista[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HOROSCOPO_ID", horoscopo.id)
            startActivity(intent)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )

    }
}