package com.example.marvelsheroes.models

data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: String
)