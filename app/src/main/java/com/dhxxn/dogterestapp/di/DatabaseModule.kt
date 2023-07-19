package com.dhxxn.dogterestapp.di

import android.app.Application
import androidx.room.Room
import com.dhxxn.data.local.AppDatabase
import com.dhxxn.data.local.AppDatabase.Companion.DB_NAME
import com.dhxxn.data.local.LikeDao
import com.dhxxn.data.mapper.ListTypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .addTypeConverter(ListTypeConverter(provideGson()))
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideLike(appDatabase: AppDatabase): LikeDao = appDatabase.likeDao()
}