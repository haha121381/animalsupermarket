package com.example.animalsupermarket.di

import android.content.Context
import androidx.room.Room
import com.example.animalsupermarket.api.ApiService
import com.example.animalsupermarket.dao.FavoriteProductDao
import com.example.animalsupermarket.database.AppDatabase
import com.example.animalsupermarket.repository.AddressRepository
import com.example.animalsupermarket.repository.CouponsRepository
import com.example.animalsupermarket.repository.FavoriteRepository
import com.example.animalsupermarket.repository.ProductRepository
import com.example.animalsupermarket.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService()
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideAddressRepository(): AddressRepository {
        return AddressRepository()
    }

    @Provides
    @Singleton
    fun provideCouponsRepository(): CouponsRepository {
        return CouponsRepository()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "animal_supermarket.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteProductDao(appDatabase: AppDatabase): FavoriteProductDao {
        return appDatabase.favoriteProductDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(favoriteProductDao: FavoriteProductDao): FavoriteRepository {
        return FavoriteRepository(favoriteProductDao)
    }
}