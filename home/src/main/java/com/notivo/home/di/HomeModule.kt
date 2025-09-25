package com.notivo.home.di

import com.notivo.common.navigation.Feature
import com.notivo.home.navigation.HomeFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Provides
    fun provideHomeFeature(): Feature {
        return HomeFeatureImpl()
    }
}