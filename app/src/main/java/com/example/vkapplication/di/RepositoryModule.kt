package com.example.vkapplication.di

import com.example.vkapplication.data.repository.AppRepositoryImpl
import com.example.vkapplication.domain.repository.AppRepository
import com.example.vkapplication.domain.usecase.GetAppByIdUseCase
import com.example.vkapplication.domain.usecase.GetAppsUseCase
import com.example.vkapplication.presentation.appdetail.AppDetailViewModel
import com.example.vkapplication.presentation.applist.AppListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { AppRepositoryImpl() } bind AppRepository::class
    factory { GetAppsUseCase(get()) }
    factory { GetAppByIdUseCase(get()) }
    viewModel { AppListViewModel(get()) }
    viewModel { AppDetailViewModel(get()) }
}
