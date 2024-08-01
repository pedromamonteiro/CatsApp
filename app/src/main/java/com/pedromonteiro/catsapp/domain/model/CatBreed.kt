package com.pedromonteiro.catsapp.domain.model

import com.pedromonteiro.catsapp.data.entity.CatBreedDTO

data class CatBreed(
    val id: String,
    val name: String,
    val referenceImageId: String?,
    val referenceImageUrl: String?,
    val lifeSpan: String,
    val averageLifeSpan: Int,
    val origin: String,
    val temperament: String,
    val description: String,
    val isFavorite: Boolean
) {
    companion object {
        fun CatBreedDTO.toCatBreed(): CatBreed {
            val referenceImageUrl = "https://cdn2.thecatapi.com/images/${referenceImageId}.jpg"
            val minLifeSpan = lifeSpan.split("-").getOrNull(0)?.trim()?.toIntOrNull() ?: 0
            val maxLifespan = lifeSpan.split("-").getOrNull(1)?.trim()?.toIntOrNull() ?: 0
            val averageLifeSpan = (minLifeSpan + maxLifespan) / 2

            return CatBreed(
                id = id,
                name = name,
                referenceImageId = referenceImageId,
                referenceImageUrl = referenceImageUrl,
                lifeSpan = lifeSpan,
                averageLifeSpan = averageLifeSpan,
                origin = origin,
                temperament = temperament,
                description = description,
                isFavorite = isFavorite
            )
        }

        fun List<CatBreedDTO>.toCatBreeds(): List<CatBreed> =
            this.map { catBreedDTO ->
                catBreedDTO.toCatBreed()
            }
    }
}