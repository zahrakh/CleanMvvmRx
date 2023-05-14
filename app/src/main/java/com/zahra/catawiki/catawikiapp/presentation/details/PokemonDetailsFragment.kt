package com.zahra.catawiki.catawikiapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.zahra.catawiki.R
import com.zahra.catawiki.databinding.FragmentPokemonDetailsBinding
import com.zahra.catawiki.utils.parcelable
import com.zahra.catawiki.utils.showHide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {

    companion object {
        const val ARG_SELECTED_POKEMON_OBJECT = "arg_selected_pokemon_object"
    }

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!


    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_SELECTED_POKEMON_OBJECT)) {
                viewModel.setSelectedPokemon(
                    it.parcelable(ARG_SELECTED_POKEMON_OBJECT)
                )
            }
        }
        observer()
    }

    private fun observer() {

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressbar.showHide(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.pokemonDetails.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.imageviewPoster.load(it.imageUrl) {
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            binding.tvDescription.text = it.description
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}