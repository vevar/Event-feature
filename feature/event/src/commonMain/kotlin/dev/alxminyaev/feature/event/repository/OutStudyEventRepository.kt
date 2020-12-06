package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.datasource.OutStudyEventDataSource
import dev.alxminyaev.feature.event.model.OutStudyEvent
import dev.alxminyaev.feature.event.rds.OutStudyEventRDS


class OutStudyEventRepository(
    private val mainDataSource: OutStudyEventDataSource,
    private val cacheDataSource: OutStudyEventDataSource? = null
) : OutStudyEventRDS {

    override suspend fun findById(id: Long): OutStudyEvent? {
        return cacheDataSource?.findById(id) ?: mainDataSource.findById(id)
    }

    override suspend fun save(entity: OutStudyEvent): Long {
        return mainDataSource.save(entity).also {
            cacheDataSource?.save(entity.copy(id = it))
        }
    }

    override suspend fun save(entities: List<OutStudyEvent>): List<Long> {
        return mainDataSource.save(entities).also {

            cacheDataSource?.run {
                val savedList = entities.mapIndexed { index, entity ->
                    entity.copy(id = it[index])
                }
                save(savedList)
            }
        }
    }

    override suspend fun update(entity: OutStudyEvent) {
        return mainDataSource.update(entity).also {
            cacheDataSource?.update(entity)
        }
    }

    override suspend fun delete(id: Long) {
        return mainDataSource.delete(id).also {
            cacheDataSource?.delete(id)
        }
    }

    override suspend fun findAll(dataLimit: OutStudyEventRDS.DataLimit): List<OutStudyEvent> {
        return try {
            mainDataSource.findAll(dataLimit).also {
                cacheDataSource?.deleteAll()
                cacheDataSource?.save(it)
            }
        } catch (e: Throwable) {
            cacheDataSource?.findAll(dataLimit) ?: throw e
        }
    }
}