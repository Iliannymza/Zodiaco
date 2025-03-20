package com.example.zodiaco

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zodiaco.data.Horoscopo

class MainActivity : AppCompatActivity() {

    val horoscopoLista = listOf(
        Horoscopo("aries" , R.string.horoscope_date_aries, R.string.horoscope_date_aries, R.drawable.aries_icon),
        Horoscopo("taurus", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus, R.drawable.taurus_icon),
        Horoscopo("gemini", R.string.horoscope_name_gemini, R.string.horoscope_date_gemini, R.drawable.gemini_icon),
        Horoscopo("cancer", R.string.horoscope_name_cancer, R.string.horoscope_date_cancer, R.drawable.cancer_icon),
        Horoscopo("leo", R.string.horoscope_name_leo, R.string.horoscope_date_leo, R.drawable.leo_icon),
        Horoscopo("virgo", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo, R.drawable.virgo_icon),
        Horoscopo("libra", R.string.horoscope_name_libra, R.string.horoscope_date_libra, R.drawable.libra_icon),
        Horoscopo("scorpio", R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio, R.drawable.scorpio_icon),
        Horoscopo("sagittarius", R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius, R.drawable.sagittarius_icon),
        Horoscopo("capricorn", R.string.horoscope_name_capricorn, R.string.horoscope_date_capricorn, R.drawable.capricorn_icon),
        Horoscopo("aquarius", R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius_icon),
        Horoscopo("pisces", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces, R.drawable.pisces_icon)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}