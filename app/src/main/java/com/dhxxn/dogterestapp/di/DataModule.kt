package com.dhxxn.dogterestapp.di

import com.dhxxn.data.datasource.RandomDogDataSource
import com.dhxxn.data.datasource.RandomDogDataSourceImpl
import com.dhxxn.data.repository.RandomDogRepositoryImpl
import com.dhxxn.domain.common.repository.RandomDogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindRandomDogDataSource(
        source: RandomDogDataSourceImpl
    ): RandomDogDataSource

    @Binds
    @Singleton
    abstract fun bindRandomDogRepository(
        repository: RandomDogRepositoryImpl
    ): RandomDogRepository

}