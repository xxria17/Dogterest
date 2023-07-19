package com.dhxxn.data.datasource

import com.dhxxn.data.local.LikeDao
import com.dhxxn.data.mapper.likeListMapper
import com.dhxxn.data.model.LikeData
import com.dhxxn.domain.common.model.Like
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LikeDogDataSourceImpl @Inject constructor(
    private val likeDao: LikeDao
): LikeDogDataSource {
    override fun requestLikeDogList(): Single<List<Like>> {
        return likeDao.getAllLikes().map { _list ->
            likeListMapper(_list)
        }
    }

    override fun addLike(like: LikeData): Completable {
        return likeDao.addLike(like)
    }

    override fun deleteLike(id: Int): Completable {
        return likeDao.deleteLike(id)
    }
}