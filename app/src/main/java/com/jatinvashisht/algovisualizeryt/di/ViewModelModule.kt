package com.jatinvashisht.algovisualizeryt.di

import com.jatinvashisht.algovisualizeryt.algorithms.InsertionSort
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Singleton
    fun providesInsertionSort(): InsertionSort = InsertionSort()
}