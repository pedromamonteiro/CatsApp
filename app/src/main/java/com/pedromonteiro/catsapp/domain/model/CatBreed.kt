package com.pedromonteiro.catsapp.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// TODO If I have time, leave this class as an entity in data package, and create model classes
//  for each kind of screen that they are needed
@Entity(tableName = "catBreed")
data class CatBreed(
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("reference_image_id") val referenceImageId: String?,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("description") val description: String,
    val isFavorite: Boolean = false
) {

    fun getImageUrl() =
        "https://cdn2.thecatapi.com/images/${referenceImageId}.jpg"

    fun getAverageLifespan() =
        (minLifeSpan + maxLifespan) / 2

    @Ignore
    private val minLifeSpan = lifeSpan.split("-").getOrNull(0)?.trim()?.toIntOrNull() ?: 0

    @Ignore
    private val maxLifespan = lifeSpan.split("-").getOrNull(1)?.trim()?.toIntOrNull() ?: 0

}