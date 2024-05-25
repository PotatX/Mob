package com.example.gosi.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class Item(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "code") val code: Int,
    @ColumnInfo(name = "surname") val surname: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)