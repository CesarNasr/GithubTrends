package com.example.githubtrends.presentation.model

/**
 * These data classes are created to prevent layers from interfering with each other,
 * also to prevent exposing the actual data layer response to the UI/Presentation layer
 * */

data class UiApiEntry(val items: List<UiItem>, val total_count: Int)

data class UiItem(
    val name: String,
    val repoName: String,
    val description: String,
    val language: String,
    val stars: Int,
    val avatarUrl : String,
    var isExpanded: Boolean = false
)