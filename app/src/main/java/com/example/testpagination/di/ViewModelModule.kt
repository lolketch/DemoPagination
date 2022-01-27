package com.example.testpagination.di

import androidx.lifecycle.ViewModel
import com.example.testpagination.ui.home.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideDepartmentViewModel(mainViewModel: MainViewModel): ViewModel
}