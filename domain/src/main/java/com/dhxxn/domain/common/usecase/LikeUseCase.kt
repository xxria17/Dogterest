package com.dhxxn.domain.common.usecase

import com.dhxxn.domain.common.model.Like
import com.dhxxn.domain.common.repository.LikeDogRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val likeDogRepository: LikeDogRepository
) {

    fun requestAllLikeDogs(): Single<List<Like>> {
        return likeDogRepository.requestListDogList()
    }

    fun deleteLikeDog(data: Like): Completable {
        return likeDogRepository.deleteLike(data)
    }

    fun addLikeDog(data: Like): Completable {
        return likeDogRepository.addLike(data)
    }

}