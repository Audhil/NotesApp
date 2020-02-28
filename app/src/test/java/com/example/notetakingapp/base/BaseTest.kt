package com.example.notetakingapp.base

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.notetakingapp.util.AppExecutors
import com.example.notetakingapp.util.ErrorLiveData
import com.example.notetakingapp.util.showVLog
import io.mockk.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executors

open class BaseTest {

    private val context by lazy {
        mockk<Context>()
    }

    val errorLiveData by lazy {
        ErrorLiveData(appExecutors)
    }

    //  private properties
    private val singleThreadExecutor by lazy {
        Executors.newSingleThreadExecutor()
    }

    val appExecutors: AppExecutors by lazy {
        mockk<AppExecutors>()
    }

    //  custom rule
    inner class InitSetUpTestRule : TestRule {
        override fun apply(base: Statement, description: Description): Statement {
            return object : Statement() {

                override fun evaluate() {
                    try {
                        preSetUps()
                        base.evaluate() //  this will run the test
                    } finally {
                        showVLog { "InitSetUpTestRule finally" }
                    }
                }

            }
        }
    }

    //  pre set up for test cases
    private fun preSetUps() {
        setUpExecutors()
        setUpLogs()
        setUpToasts()
    }

    private fun setUpToasts() {
        mockkStatic(Toast::class)
        every { Toast.makeText(any(), any<String>(), any()) } returns Toast(context)
        every { Toast.makeText(any(), any<String>(), any()).show() } just Runs
        every { Toast.makeText(any(), any<String>(), any()).cancel() } just Runs
    }

    private fun setUpExecutors() {
        every { appExecutors.diskIOThread() } returns singleThreadExecutor
        every { appExecutors.networkIOThread() } returns singleThreadExecutor
        every { appExecutors.mainThread() } returns singleThreadExecutor
    }

    private fun setUpLogs() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }
}