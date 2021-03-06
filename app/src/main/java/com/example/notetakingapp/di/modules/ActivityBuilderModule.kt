package com.example.notetakingapp.di.modules

import com.example.notetakingapp.di.factories.ViewModelBuilder
import com.example.notetakingapp.ui.create.CreateActivity
import com.example.notetakingapp.ui.detail.DetailActivity
import com.example.notetakingapp.ui.list.MainActivity
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

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    abstract fun bindCreateActivity(): CreateActivity
}