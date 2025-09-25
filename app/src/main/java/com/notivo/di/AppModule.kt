package com.notivo.di

import com.notivo.DefaultNavigator
import com.notivo.home.navigation.HomeFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideDefaultNavigator(
        homeFeature: HomeFeature
    ): DefaultNavigator {
        return DefaultNavigator(homeFeature)
    }
}