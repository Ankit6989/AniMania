package com.apcoding.animania.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LinkDao {
    @Query("SELECT * FROM fav_table where favSource = :sourceName")
    fun getLinks(sourceName: String? = "yugen"): Flow<List<FavRoomModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav: FavRoomModel)

    @Delete
    fun deleteOne(fav: FavRoomModel)

    @Query("SELECT EXISTS (SELECT * FROM fav_table where favLink = :link AND favSource = :sourceName)")
    fun isItFav(link: String, sourceName: String): Boolean

    @Query("SELECT * FROM fav_table where favLink = :link AND favSource = :sourceName")
    fun getFav(link: String, sourceName: String): FavRoomModel

}