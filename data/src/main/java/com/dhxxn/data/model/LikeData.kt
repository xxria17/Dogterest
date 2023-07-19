package com.dhxxn.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikeData(
    val imageUrl: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
