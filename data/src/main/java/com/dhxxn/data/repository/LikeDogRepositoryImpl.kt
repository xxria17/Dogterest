package com.dhxxn.data.repository

import com.dhxxn.data.datasource.LikeDogDataSource
import com.dhxxn.data.mapper.likeMapper
import com.dhxxn.domain.common.model.Like
import com.dhxxn.domain.common.repository.LikeDogRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LikeDogRepositoryImpl @Inject constructor(
    private val dataSource: LikeDogDataSource
): LikeDogRepository {
    override fun requestListDogList(): Single<List<Like>> {
        return dataSource.requestLikeDogList()
    }

    override fun addLike(data: Like): Completable {
        return dataSource.addLike(likeMapper(data))
    }

    override fun deleteLike(id: Int): Completable {
        return dataSource.deleteLike(id)
    }
}