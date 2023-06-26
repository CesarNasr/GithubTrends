package com.example.githubtrends.data.utils

interface EntityMapper <Entity, DomainModel>{
    fun mapToEntity(domainModel: DomainModel): Entity
}