package com.example.vkapplication.di

import androidx.room.Room
import com.example.vkapplication.data.appdetails.local.AppDatabase
import com.example.vkapplication.data.appdetails.local.AppDetailsEntityMapper
import com.example.vkapplication.data.appdetails.mapper.AppDetailsMapper
import com.example.vkapplication.data.network.CatalogApi
import com.example.vkapplication.data.repository.AppDetailsRepositoryImpl
import com.example.vkapplication.data.repository.AppRepositoryImpl
import com.example.vkapplication.domain.repository.AppDetailsRepository
import com.example.vkapplication.domain.repository.AppRepository
import com.example.vkapplication.domain.usecase.GetAppDetailsUseCase
import com.example.vkapplication.domain.usecase.GetAppsUseCase
import com.example.vkapplication.domain.usecase.ObserveAppDetailsUseCase
import com.example.vkapplication.domain.usecase.ToggleFavoriteUseCase
import com.example.vkapplication.presentation.appdetail.AppDetailViewModel
import com.example.vkapplication.presentation.applist.AppListViewModel
import org.koin.android.ext.koin.androidContext
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
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(true).build()
    }
    single { get<AppDatabase>().appDetailsDao() }
    single { AppDetailsMapper() }
    single { AppDetailsEntityMapper() }
    single { get<Retrofit>().create(CatalogApi::class.java) }
    single { AppRepositoryImpl(get()) } bind AppRepository::class
    single { AppDetailsRepositoryImpl(get(), get(), get(), get()) } bind AppDetailsRepository::class
    factory { GetAppsUseCase(get()) }
    factory { GetAppDetailsUseCase(get()) }
    factory { ObserveAppDetailsUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
    viewModel { AppListViewModel(get()) }
    viewModel { (appId: String) -> AppDetailViewModel(appId, get(), get(), get()) }
}
