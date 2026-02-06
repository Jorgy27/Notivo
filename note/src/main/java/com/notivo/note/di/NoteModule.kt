package com.notivo.note.di

import com.notivo.note.navigation.NoteFeature
import com.notivo.note.navigation.NoteFeatureImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NoteModule {

    @Provides
    fun provideNoteFeature(): NoteFeature {
        return NoteFeatureImpl()
    }
}