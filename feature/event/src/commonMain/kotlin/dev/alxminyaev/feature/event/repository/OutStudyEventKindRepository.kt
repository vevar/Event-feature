package dev.alxminyaev.feature.event.repository

import dev.alxminyaev.feature.event.datasource.OutStudyEventKindDataSource
import dev.alxminyaev.feature.event.model.OutStudyEventKind
import dev.alxminyaev.feature.event.rds.OutStudyEventKindRDS


class OutStudyEventKindRepository(
    private val mainDataSource: OutStudyEventKindDataSource,
    private val cacheDataSource: OutStudyEventKindDataSource? = null
) : OutStudyEventKindRDS {

    override suspend fun findById(id: Int): OutStudyEventKind? {
        return cacheDataSource?.findById(id) ?: mainDataSource.findById(id)
    }

    override suspend fun save(entity: OutStudyEventKind): Int {
        return mainDataSource.save(entity).also {
            cacheDataSource?.save(entity.copy(id = it))
        }
    }

    override suspend fun save(entities: List<OutStudyEventKind>): List<Int> {
        return mainDataSource.save(entities).also {

            cacheDataSource?.run {
                val savedList = entities.mapIndexed { index, entity ->
                    entity.copy(id = it[index])
                }
                save(savedList)
            }
        }
    }

    override suspend fun update(entity: OutStudyEventKind) {
        return mainDataSource.update(entity).also {
            cacheDataSource?.update(entity)
        }
    }

    override suspend fun delete(id: Int) {
        return mainDataSource.delete(id).also {
            cacheDataSource?.delete(id)
        }
    }


    override suspend fun findAll(): List<OutStudyEventKind> {
        return try {
            mainDataSource.findAll().also {
                cacheDataSource?.deleteAll()
                cacheDataSource?.save(it)
            }
        } catch (e: Throwable) {
            cacheDataSource?.findAll() ?: throw e
        }
    }

}