package com.apcoding.animania.animesources

import android.content.Context
import com.apcoding.animania.source.AnimeSource

class SourceSelector(context: Context) {
    val sourceMap: Map<String, AnimeSource> = mapOf(
        "yugen" to YugenSource(),
    )

    fun getSelectedSource(selectedSource: String): AnimeSource{
        if (selectedSource in sourceMap.keys){
            return sourceMap[selectedSource]!!
        }
        return sourceMap["yugen"]!!
    }
}