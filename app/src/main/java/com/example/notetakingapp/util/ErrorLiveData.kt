package com.example.notetakingapp.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class ErrorLiveData
@Inject
constructor(private val executor: AppExecutors) : LiveData<AppError>() {

    private val pending = AtomicBoolean(false)

    fun setAppError(value: AppError) =
        executor.mainThread().execute {
            pending.set(true)
            super.setValue(value)
        }

    override fun observe(owner: LifecycleOwner, observer: Observer<in AppError>) {
        super.observe(owner, Observer<AppError> { value ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(value)
            }
        })
    }
}