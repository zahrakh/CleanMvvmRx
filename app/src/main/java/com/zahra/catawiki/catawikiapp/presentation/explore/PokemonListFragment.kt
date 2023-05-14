package com.zahra.catawiki.catawikiapp.presentation.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zahra.catawiki.R
import com.zahra.catawiki.catawikiapp.domain.model.Pokemon
import com.zahra.catawiki.catawikiapp.presentation.details.PokemonDetailsFragment.Companion.ARG_SELECTED_POKEMON_OBJECT
import com.zahra.catawiki.databinding.FragmentPokemonListBinding
import com.zahra.catawiki.utils.safeNavigate
import com.zahra.catawiki.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels()

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val adapter = PokemonAdapter(
        onLoadMoreListener = {
            viewModel.getPokemons()
        },
        showEmptyState = {
            binding.tvEmptyState.show()
        },
        click = { pokemon, position ->
            navigateToDetailsFragment(pokemon,position)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        recycler()
    }

    private fun observer() {

        viewModel.loading.observe(viewLifecycleOwner) {
            adapter.isLoading = it
        }

        viewModel.error.observe(viewLifecycleOwner) {
            adapter.isError = it
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter.appendData(it.results, it.count)
        }
    }

    private fun recycler() {
        binding.recyclerView.adapter = adapter
    }

    private fun navigateToDetailsFragment(pokemon: Pokemon, position: Int) {
        safeNavigate(
            R.id.action_pokemonListFragment_to_pokemonDetailsFragment,
            Bundle().apply {
                putParcelable(ARG_SELECTED_POKEMON_OBJECT, pokemon.apply {
                    this.id=position+1
                })
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}