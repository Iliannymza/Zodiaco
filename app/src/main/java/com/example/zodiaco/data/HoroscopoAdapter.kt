package com.example.zodiaco.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.zodiaco.R

class HoroscopoAdapter(val items: List<Horoscopo>, val onItemClick: (Int) -> Unit) : Adapter<HoroscopoViewHolder>() {

    //Cual es la vista de la celdas
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscopo, parent, false)
        return HoroscopoViewHolder(view)
    }

    //Cuantos elementos tengo que listar
    override fun getItemCount(): Int {
        return items.size
    }

    //Voy a mostrar la celda en la posicion indicada
    override fun onBindViewHolder(holder: HoroscopoViewHolder, position: Int) {
        val horoscopo = items[position]
        holder.render(horoscopo)
        holder.itemView.setOnClickListener {
            onItemClick(position)

        }
    }
}

class HoroscopoViewHolder(view: View) : ViewHolder(view) {

    val  nameTextView: TextView = view.findViewById(R.id.nameTextView)
    val datesTextView : TextView = view.findViewById(R.id.datesTextView)
    val iconImageView : ImageView = view.findViewById(R.id.iconImageView)

    fun render(horoscopo: Horoscopo) {
        nameTextView.setText(horoscopo.name)
        datesTextView.setText(horoscopo.dates)
        iconImageView.setImageResource(horoscopo.icon)
    }
}


