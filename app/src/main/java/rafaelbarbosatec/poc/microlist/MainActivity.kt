package rafaelbarbosatec.poc.microlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rafaelbarbosatec.sdk.RepositoryBuilder
import com.rafaelbarbosatec.sdk.data.pokemon.model.Pokemon
import rafaelbarbosatec.poc.listpokemon.PokemonList
import rafaelbarbosatec.poc.listpokemon.BuildConfig
import rafaelbarbosatec.poc.listpokemon.PokemonListComunication

class MainActivity : AppCompatActivity(), PokemonListComunication.PokemonListListern {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PokemonList.show(supportFragmentManager,R.id.container_list,this)

    }

    override fun pokemonOnClick(pokemon: Pokemon) {
        Log.i("fddf",pokemon.toString())
    }
}
