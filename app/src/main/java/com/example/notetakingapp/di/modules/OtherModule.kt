package com.example.notetakingapp.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.TestScheduler

@Module
class OtherModule {

    @Provides
    fun giveTestScheduler(): TestScheduler? = null
}