package com.example.zodiaco.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiaco.R
import com.example.zodiaco.data.HoroscopoProvider

class DetailActivity : AppCompatActivity() {

    lateinit var nameTextView: TextView
    lateinit var datesTextView: TextView
    lateinit var iconImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameTextView = findViewById(R.id.nameTextView)
        datesTextView = findViewById(R.id.datesTextView)
        iconImageView = findViewById(R.id.iconImageView)

       val id = intent.getStringExtra("HOROSCOPO_ID")!!

       val horoscopo =  HoroscopoProvider.getById(id)!!

        nameTextView.setText(horoscopo.name)
        datesTextView.setText(horoscopo.dates)
        iconImageView.setImageResource(horoscopo.icon)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorito -> {
                Toast.makeText(this,"Favorito", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_share -> {
                Toast.makeText(this,"Compartir", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)

            }
        }
    }
}