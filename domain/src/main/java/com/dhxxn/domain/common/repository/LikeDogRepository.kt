package com.dhxxn.domain.common.repository

import com.dhxxn.domain.common.model.Like
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface LikeDogRepository {
    fun requestListDogList(): Single<List<Like>>
    fun addLike(data: Like): Completable
    fun deleteLike(data: Like): Completable
}