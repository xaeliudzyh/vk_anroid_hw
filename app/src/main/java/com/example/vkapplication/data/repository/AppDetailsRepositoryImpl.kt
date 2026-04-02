package com.example.vkapplication.data.repository

import com.example.vkapplication.data.appdetails.local.AppDetailsDao
import com.example.vkapplication.data.appdetails.local.AppDetailsEntityMapper
import com.example.vkapplication.data.appdetails.mapper.AppDetailsMapper
import com.example.vkapplication.data.network.CatalogApi
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class AppDetailsRepositoryImpl(
    private val appApi: CatalogApi,
    private val dao: AppDetailsDao,
    private val appDetailsMapper: AppDetailsMapper,
    private val appDetailsEntityMapper: AppDetailsEntityMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AppDetailsRepository {
    override suspend fun getAppDetails(id: String): App? {
        val entity = dao.getAppDetails(id).first()
        if (entity != null) {
            return appDetailsEntityMapper.toDomain(entity)
        }

        return runCatching {
            val dto = appApi.getAppById(id)
            val data = appDetailsMapper.toData(dto)
            val dbEntity = appDetailsEntityMapper.toEntity(data)
            withContext(ioDispatcher) {
                dao.insertAppDetails(dbEntity)
            }
            appDetailsEntityMapper.toDomain(dbEntity)
        }.getOrNull()
    }
}

