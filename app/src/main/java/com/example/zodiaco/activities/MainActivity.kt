package com.example.zodiaco.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.OnReceiveContentListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zodiaco.data.HoroscopoAdapter
import com.example.zodiaco.R
import com.example.zodiaco.data.HoroscopoProvider


class MainActivity : AppCompatActivity() {

    var horoscopoLista = HoroscopoProvider.getAll()

    lateinit var recyclerView: RecyclerView
    lateinit var  adapter: HoroscopoAdapter

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

    }

    override fun onResume() {
        super.onResume()
        adapter = HoroscopoAdapter(horoscopoLista, { position ->
            val horoscopo = horoscopoLista[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HOROSCOPO_ID", horoscopo.id)
            startActivity(intent)
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)

        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                horoscopoLista = HoroscopoProvider.getAll().filter { horoscopo ->
                    getString(horoscopo.name).contains(newText, true)
                }
                adapter.updateItems(horoscopoLista)
                return true
            }
        })

        return true
    }
}