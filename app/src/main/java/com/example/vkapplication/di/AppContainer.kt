package com.example.vkapplication.di

import com.example.vkapplication.data.repository.StaticAppRepository
import com.example.vkapplication.domain.repository.AppRepository
import com.example.vkapplication.domain.usecase.GetAppByIdUseCase
import com.example.vkapplication.domain.usecase.GetAppsUseCase

object AppContainer {
    private val repository: AppRepository = StaticAppRepository

    val getAppsUseCase: GetAppsUseCase = GetAppsUseCase(repository)
    val getAppByIdUseCase: GetAppByIdUseCase = GetAppByIdUseCase(repository)
}

