package com.example.pokedexkotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {

    interface PokemonSelectListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }

    private lateinit var pokemonSelectListener: PokemonSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonSelectListener = try {
            context as PokemonSelectListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement PokemonSelectListener")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = PokemonAdapter()
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            pokemonSelectListener.onPokemonSelected(it)
//            Toast.makeText(requireActivity(), it.name, Toast.LENGTH_SHORT).show()
        }

        val pokemonList = mutableListOf(
            Pokemon(1, "Bulbasaur", 45, 49,
                49, 45, Pokemon.PokemonType.GRASS),
            Pokemon(
                2, "Ivysaur", 60, 62,
                63, 60,  Pokemon.PokemonType.GRASS),
            Pokemon(
                3, "Venuasaur", 80, 82,
                83, 80, Pokemon.PokemonType.GRASS),
            Pokemon(
                4, "Charmander", 39, 52,
                43, 65, Pokemon.PokemonType.FIRE),
            Pokemon(
                5, "Charmeleon", 58, 64,
                58, 80, Pokemon.PokemonType.FIRE),
            Pokemon(
                6, "Charizzard", 78, 84,
                78, 100, Pokemon.PokemonType.FIRE),
            Pokemon(
                7, "Squirtle", 44, 48,
                65, 43, Pokemon.PokemonType.WATER),
            Pokemon(
                8, "Wartortle", 59, 63,
                80, 58, Pokemon.PokemonType.WATER),
            Pokemon(
                9, "Blastoise", 79, 83,
                100, 78, Pokemon.PokemonType.WATER),
            Pokemon(
                25, "Pikachu", 35, 55,
                40, 90, Pokemon.PokemonType.ELECTRIC),
            Pokemon(
                26, "Raichu", 60, 90,
                55, 110, Pokemon.PokemonType.ELECTRIC),
        )

        adapter.submitList(pokemonList)





        return view
    }


}