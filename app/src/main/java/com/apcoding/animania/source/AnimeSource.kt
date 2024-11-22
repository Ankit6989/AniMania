package com.apcoding.animania.source

import com.apcoding.animania.model.AnimeDetails
import com.apcoding.animania.model.AnimeStreamLink
import com.apcoding.animania.model.SimpleAnime

interface AnimeSource {
    suspend fun animeDetails(contentLink: String): AnimeDetails
    suspend fun searchAnime(searchedText: String): ArrayList<SimpleAnime>
    suspend fun latestAnime(): ArrayList<SimpleAnime>
    suspend fun trendingAnime(): ArrayList<SimpleAnime>
    suspend fun streamLink(
        animeUrl: String,
        animeEpCode: String,
        extras: List<String>? = null
    ): AnimeStreamLink
}