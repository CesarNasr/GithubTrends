package com.example.githubtrends.presentation.model

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