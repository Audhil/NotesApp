package com.example.notetakingapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.repository.DataRepository
import com.example.notetakingapp.ui.base.BaseViewModel
import com.example.notetakingapp.util.AppError
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val dataRepository: DataRepository
) : BaseViewModel() {

    val errorLiveData: LiveData<AppError> = dataRepository.errorLiveData

    val triggerLiveData = MutableLiveData<Boolean>()

    val notesLiveData: LiveData<List<NoteModel>> = Transformations.switchMap(triggerLiveData) {
        MutableLiveData<List<NoteModel>>().apply {
            value = dataRepository.getNotes()
        }
    }
}