package com.dhxxn.data.datasource

import com.dhxxn.data.model.LikeData
import com.dhxxn.domain.common.model.Like
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface LikeDogDataSource {
    fun requestLikeDogList(): Single<List<Like>>

    fun addLike(like: LikeData): Completable

    fun deleteLike(id: Int): Completable
}