package com.example.notetakingapp.di.modules

import com.example.notetakingapp.di.factories.ViewModelBuilder
import com.example.notetakingapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}