package com.example.vkapplication.di

import com.example.vkapplication.data.network.CatalogApi
import com.example.vkapplication.data.repository.AppRepositoryImpl
import com.example.vkapplication.domain.repository.AppRepository
import com.example.vkapplication.domain.usecase.GetAppByIdUseCase
import com.example.vkapplication.domain.usecase.GetAppsUseCase
import com.example.vkapplication.presentation.appdetail.AppDetailViewModel
import com.example.vkapplication.presentation.applist.AppListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://185.103.109.134/"

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(CatalogApi::class.java) }
    single { AppRepositoryImpl(get()) } bind AppRepository::class
    factory { GetAppsUseCase(get()) }
    factory { GetAppByIdUseCase(get()) }
    viewModel { AppListViewModel(get()) }
    viewModel { AppDetailViewModel(get()) }
}
