package com.zahra.catawiki.catawikiapp.presentation.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zahra.catawiki.databinding.FragmentPokemonListBinding
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
            adapter.isLoading = true
        }

        viewModel.error.observe(viewLifecycleOwner) {
            adapter.isError = true
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) {
            adapter.appendData(it.results, it.count)
        }

    }

    private fun recycler() {
        binding.recyclerView.adapter=adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}