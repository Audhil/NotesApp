package com.example.notetakingapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.notetakingapp.di.factories.ViewModelKey
import com.example.notetakingapp.ui.create.CreateViewModel
import com.example.notetakingapp.ui.list.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateViewModel::class)
    abstract fun bindCreateViewModel(viewModel: CreateViewModel): ViewModel
}