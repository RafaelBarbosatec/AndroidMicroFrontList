package rafaelbarbosatec.poc.listpokemon


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelbarbosatec.sdk.data.pokemon.model.TypePokemon
import com.superdigital.poc.ui.util.extensions.listen
import com.superdigital.poc.ui.view.home.adapter.PokemonAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import rafaelbarbosatec.poc.listpokemon.di.MicroListInjection

class PokemonList : Fragment() {

    companion object{
        var listener: PokemonListComunication.PokemonListListern? = null
        var actions: PokemonListComunication.PokemonListAction? = null
        var container:Int ? = null
        fun show(fragMagaer: FragmentManager,@IdRes container:Int,listener: PokemonListComunication.PokemonListListern){
            this.listener = listener
            this.container = container
            val ft = fragMagaer.beginTransaction()
            ft.replace(container, PokemonList())
            ft.commit()
        }

        fun getModuleInjection(): Module {
            return MicroListInjection.module
        }
    }

    private val viewModel: PokemonListViewModel  by viewModel()

    private val adapter = PokemonAdapter(arrayListOf()) {
        listener?.pokemonOnClick(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        viewModel.loadPokemons()
    }

    override fun onResume() {
        super.onResume()
        actions = object:PokemonListComunication.PokemonListAction{
            override fun filterByType(type: TypePokemon?) {
                viewModel.filterType(type)
            }
        }
    }

    private fun initObservers() {
        viewModel.pokemons.listen(this){
            adapter.replaceData(it)
        }
        viewModel.progress.listen(this){
            progress_circular.visibility = if(it){
                View.VISIBLE
            }else{
                View.GONE
            }
        }
    }

    private fun initViews() {
        recyclerview_pokemon.layoutManager = LinearLayoutManager(activity)
        recyclerview_pokemon.adapter = adapter
    }

}


