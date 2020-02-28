package com.example.notetakingapp.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notetakingapp.base.BaseTest
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.repository.DataRepository
import com.example.notetakingapp.util.AppError
import io.mockk.spyk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DataRepositoryTest : BaseTest() {

    @get:Rule
    val instantExecutorRule by lazy {
        InstantTaskExecutorRule()
    }

    @get:Rule
    val initSetUpTestRule by lazy {
        InitSetUpTestRule()
    }

    private var dataRepo: DataRepository? = null

    @Before
    fun `set up`() {
        dataRepo = spyk(DataRepository(errorLiveData))
    }

    @After
    fun `tear down`() {
        dataRepo = null
    }

    @Test
    fun `add note with non null value`() {
        dataRepo?.addNote(NoteModel())
        assert(dataRepo?.errorLiveData?.value == null)
    }

    @Test
    fun `add note with null value`() {
        dataRepo?.addNote(null)
        assert(dataRepo?.errorLiveData?.value == AppError.NOTE_NOT_AVAILABLE)
    }
}