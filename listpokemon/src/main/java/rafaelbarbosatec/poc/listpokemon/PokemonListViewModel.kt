package rafaelbarbosatec.poc.listpokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafaelbarbosatec.sdk.core.extensions.read
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import com.rafaelbarbosatec.sdk.data.pokemon.model.TypePokemon
import com.rafaelbarbosatec.sdk.data.pokemon.repository.PokemonRepository
import com.superdigital.poc.ui.util.extensions.asMutable

class PokemonListViewModel(private val repository: PokemonRepository?): ViewModel(){

    val pokemons : LiveData<ArrayList<Pokemon>> = MutableLiveData()
    val progress: LiveData<Boolean> = MutableLiveData()

    private var pokemonsList:ArrayList<Pokemon> = arrayListOf()

    fun loadPokemons(){

        progress.asMutable?.value = true

        repository?.pokemons { any ->

            any.read({
                pokemonsList = it
                pokemons.asMutable?.value = pokemonsList
            },{

            })

            progress.asMutable?.value = false

        }
    }

    fun filterType(type: TypePokemon?) {
        if(type == null){
            pokemons.asMutable?.value = pokemonsList
            return
        }
        val p = pokemonsList.filter { pokemon -> pokemon.type.contains(type.name)  }
        pokemons.asMutable?.value = ArrayList(p)
    }

    override fun onCleared() {
        repository?.destroy()
        super.onCleared()
    }
}