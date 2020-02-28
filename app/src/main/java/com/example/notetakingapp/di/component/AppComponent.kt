package com.example.notetakingapp.di.component

import android.content.Context
import com.example.notetakingapp.NotesApplication
import com.example.notetakingapp.di.modules.ActivityBuilderModule
import com.example.notetakingapp.di.modules.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<NotesApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}
