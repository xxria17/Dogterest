package com.dhxxn.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dhxxn.data.model.LikeData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface LikeDao {

    @Query(value = "SELECT * FROM LikeData")
    fun getAllLikes(): Single<List<LikeData>>

    @Insert
    fun addLike(data: LikeData): Completable

    @Delete
    fun deleteLike(data: LikeData): Completable
}