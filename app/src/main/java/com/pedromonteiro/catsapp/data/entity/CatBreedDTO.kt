package com.pedromonteiro.catsapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "catBreed")
data class CatBreedDTO(
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("reference_image_id") val referenceImageId: String?,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("description") val description: String,
    val isFavorite: Boolean = false
)