package com.firstniwt.newsapp.api

data class Result(
    val apiUrl: String,
    val id: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String
)