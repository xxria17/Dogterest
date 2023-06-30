package com.dhxxn.dogterestapp.model

import com.dhxxn.domain.common.model.Dog

data class DogUiModel(
    val imageUrl: String,
    val status: String
)

fun Dog.toUiModel(): DogUiModel {
    return DogUiModel(
        imageUrl = imageUrl,
        status = status
    )
}