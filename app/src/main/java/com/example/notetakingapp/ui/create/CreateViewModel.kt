package com.example.notetakingapp.ui.create

import androidx.lifecycle.LiveData
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.repository.DataRepository
import com.example.notetakingapp.ui.base.BaseViewModel
import com.example.notetakingapp.util.AppError
import javax.inject.Inject

class CreateViewModel
@Inject
constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    val errorLiveData: LiveData<AppError> = dataRepository.errorLiveData

    fun addNote(note: NoteModel) = dataRepository.addNote(note)
}