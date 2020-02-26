package com.example.notetakingapp

import com.example.notetakingapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NotesApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(applicationContext)

    companion object {
        lateinit var INSTANCE: NotesApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}