package com.superdigital.poc.ui.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import com.superdigital.poc.ui.util.MyViewHolder
import com.superdigital.poc.ui.util.extensions.loadFromUrl
import kotlinx.android.synthetic.main.item_pokemon.view.*
import rafaelbarbosatec.poc.listpokemon.R

class PokemonAdapter(
    private var pokemons:ArrayList<Pokemon> = arrayListOf(),
    val clickItem:(Pokemon) ->Unit
) : RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = pokemons[position]

        with(holder.itemView){
            imageview_pokemon.loadFromUrl(item.thumbnailImage)
            textview_name.text = item.name
            setOnClickListener {
                clickItem(item)
            }
        }
    }

    fun replaceData(pokemons:ArrayList<Pokemon>){
        this.pokemons = pokemons
        notifyDataSetChanged()
    }

}
