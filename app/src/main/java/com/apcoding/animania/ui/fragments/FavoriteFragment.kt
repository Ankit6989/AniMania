package com.apcoding.animania.ui.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.apcoding.animania.adapter.AnimeRecyclerAdapter
//import com.apcoding.animania.adapter.AnimeRecyclerAdapter
import com.apcoding.animania.databinding.FragmentFavoriteBinding
import com.apcoding.animania.ui.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment: Fragment() {

    private var _binding: FragmentFavoriteBinding? =null
    private val favoriteViewModel : FavoriteViewModel by viewModels()
    private val selectedSource by lazy {
        PreferenceManager
            .getDefaultSharedPreferences(requireActivity())
            .getString("source", "yugen")
    }
    private val rvAdapter by lazy {
        AnimeRecyclerAdapter(if (selectedSource == "yugen") "landscape card" else "portrait card")
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.progressbarInMain.visibility = View.VISIBLE
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerView.layoutManager = GridLayoutManager(activity as Context, 4)
        } else {
            binding.recyclerView.layoutManager = GridLayoutManager(activity as Context, 2)
        }
        binding.recyclerView.adapter = rvAdapter

        binding.recyclerView.setHasFixedSize(true)

        favoriteViewModel.favoriteAnimeList.observe(viewLifecycleOwner) {
            binding.progressbarInMain.visibility = View.GONE
            if (it.isNotEmpty()) {
                binding.errorCard.visibility = View.GONE
            } else {
                binding.errorCard.visibility = View.VISIBLE
            }
            rvAdapter.submitList(it)
            if (binding.swipeContainer.isRefreshing) {
                binding.swipeContainer.isRefreshing = false
            }
        }

        binding.swipeContainer.setOnRefreshListener { favoriteViewModel.getFavorites() }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavorites()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (activity != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                binding.recyclerView.layoutManager = GridLayoutManager(activity as Context, 2)
            } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                binding.recyclerView.layoutManager = GridLayoutManager(activity as Context, 4)
            }
        }
    }
}
