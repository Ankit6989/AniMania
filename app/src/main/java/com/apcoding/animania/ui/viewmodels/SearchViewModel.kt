package com.apcoding.animania.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apcoding.animania.model.SimpleAnime
import com.apcoding.animania.repo.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    private val _searchedAnimeList = MutableLiveData<ArrayList<SimpleAnime>>()
    val searchedAnimeList: LiveData<ArrayList<SimpleAnime>> = _searchedAnimeList

    fun searchAnime(searchUrl: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                animeRepository.searchAnimeFromSite(searchUrl).apply {
                    _searchedAnimeList.postValue(this@apply)
                }

            }
        }
    }

}