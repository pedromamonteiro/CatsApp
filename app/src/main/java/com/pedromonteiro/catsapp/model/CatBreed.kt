package com.pedromonteiro.catsapp.model

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("reference_image_id") val referenceImageId: String,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("description") val description: String,
    val isFavorite: Boolean = false
) {

    fun getImageUrl() =
        "https://cdn2.thecatapi.com/images/${referenceImageId}.jpg"
}