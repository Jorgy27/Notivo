package com.notivo.mylists.di

import com.notivo.mylists.navigation.MyListsFeature
import com.notivo.mylists.navigation.MyListsFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MyListsModule {
    @Provides
    fun provideMyListsFeature(): MyListsFeature {
        return MyListsFeatureImpl()
    }
}