package com.example.githubtrends.data.model.response

data class ApiEntry(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)