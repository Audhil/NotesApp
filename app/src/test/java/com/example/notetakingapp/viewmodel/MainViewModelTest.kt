package com.example.notetakingapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notetakingapp.base.BaseTest
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.repository.DataRepository
import com.example.notetakingapp.ui.list.MainViewModel
import com.example.notetakingapp.util.getOrAwaitValue
import io.mockk.every
import io.mockk.spyk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest : BaseTest() {

    @get:Rule
    val instantExecutorRule by lazy {
        InstantTaskExecutorRule()
    }

    @get:Rule
    val initSetUpTestRule by lazy {
        InitSetUpTestRule()
    }

    private var mainViewModel: MainViewModel? = null
    private var dataRepo: DataRepository = spyk(DataRepository(errorLiveData))

    @Before
    fun `set up`() {
        mainViewModel = spyk(MainViewModel(dataRepo))
    }

    @After
    fun `tear down`() {
        mainViewModel = null
    }

    @Test
    fun `test case with empty list`() {
        val emptyList = emptyList<NoteModel>()
        every { dataRepo.getNotes() } returns emptyList
        mainViewModel?.triggerLiveData?.value = true
        assert(mainViewModel?.notesLiveData?.getOrAwaitValue() != null)
        assert(mainViewModel?.notesLiveData?.getOrAwaitValue() == emptyList)
    }

    @Test
    fun `test case with non empty list`() {
        val noteList = arrayListOf<NoteModel>().apply {
            add(NoteModel())
        }
        every { dataRepo.getNotes() } returns noteList
        mainViewModel?.triggerLiveData?.value = true
        assert(mainViewModel?.notesLiveData?.getOrAwaitValue() != null)
        assert(mainViewModel?.notesLiveData?.getOrAwaitValue() == noteList)
        assert(mainViewModel?.notesLiveData?.getOrAwaitValue()?.size == 1)
    }
}