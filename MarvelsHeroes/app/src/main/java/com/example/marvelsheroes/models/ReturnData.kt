package com.example.marvelsheroes.models

data class ReturnData(
    val `data`: Data,
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val etag: String,
    val status: String
)