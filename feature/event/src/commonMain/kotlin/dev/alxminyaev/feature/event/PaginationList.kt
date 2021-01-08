package dev.alxminyaev.feature.event

data class PaginationList<D>(
    val size: Long,
    val data: List<D>
)