package rafaelbarbosatec.poc.listpokemon.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rafaelbarbosatec.poc.listpokemon.PokemonListViewModel

object MicroListInjection {

    val module = module {
        factory { CreateModules.pokemonRepository(get()) }

        ///ViewModels
        viewModel { PokemonListViewModel(get()) }
    }

}