package com.dhxxn.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LikeData")
data class LikeData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name="imageUrl") val imageUrl: String
)
