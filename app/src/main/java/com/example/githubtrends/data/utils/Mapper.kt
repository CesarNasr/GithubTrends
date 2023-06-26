package com.example.githubtrends.data.utils

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.model.response.Item
import com.example.githubtrends.presentation.model.UiApiEntry
import com.example.githubtrends.presentation.model.UiItem
import javax.inject.Inject

/**
 * Each level of our app (local data source/ remote datasource / UI) has it's own entity/ models and this class helps us map
 * them to one another
 */
class ItemMapper @Inject constructor() : EntityMapper<UiApiEntry, ApiEntry> {

    override fun mapToEntity(domainModel: ApiEntry): UiApiEntry {
        return UiApiEntry(
            items = mapToEntityList(domainModel.items),
            total_count = domainModel.total_count
        )
    }

    private fun mapToEntityList(entities: List<Item>): List<UiItem> {
        return entities.map {
            UiItem(
                name = it.owner.login,
                repoName = it.name,
                description = it.description,
                language = it.language,
                stars = it.stargazers_count
            )
        }
    }
}














