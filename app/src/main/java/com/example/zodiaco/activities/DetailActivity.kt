package com.example.zodiaco.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiaco.R
import com.example.zodiaco.data.Horoscopo
import com.example.zodiaco.data.HoroscopoProvider
import com.example.zodiaco.utils.sessionManager


class DetailActivity : AppCompatActivity() {

    lateinit var nameTextView: TextView
    lateinit var datesTextView: TextView
    lateinit var iconImageView: ImageView

    lateinit var session: sessionManager
    lateinit var horoscopo: Horoscopo
    var isFavorito = false
    lateinit var favoritoMenuItem: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        session = sessionManager(this)

        nameTextView = findViewById(R.id.nameTextView)
        datesTextView = findViewById(R.id.datesTextView)
        iconImageView = findViewById(R.id.iconImageView)

       val id = intent.getStringExtra("HOROSCOPO_ID")!!

        horoscopo = HoroscopoProvider.getById(id)!!

        isFavorito = session.getFavoritoHoroscopo() == horoscopo.id

        nameTextView.setText(horoscopo.name)
        datesTextView.setText(horoscopo.dates)
        iconImageView.setImageResource(horoscopo.icon)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)

        favoritoMenuItem = menu.findItem(R.id.menu_favorito)
        setFavoritoIcon()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorito -> {
                if (isFavorito) {
                    session.setFavoritoHoroscopo("")
                } else {
                    session.setFavoritoHoroscopo(horoscopo.id)
                }
                isFavorito = !isFavorito
                setFavoritoIcon()

                return true
            }

            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    fun  setFavoritoIcon () {
        if (isFavorito) {
            favoritoMenuItem.setIcon(R.drawable.ic_favorito_selecionado)
        } else {
            favoritoMenuItem.setIcon(R.drawable.ic_favorito)
        }
    }
}