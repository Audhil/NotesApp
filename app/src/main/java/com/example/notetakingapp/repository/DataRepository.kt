package com.example.notetakingapp.repository

import com.example.notetakingapp.data.NoteModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository
@Inject
constructor() : IDataRepository {

    private val notesList by lazy {
        arrayListOf<NoteModel>()
    }

    override fun addNote(note: NoteModel) {
        notesList.add(note)
    }

    override fun getNotes(): List<NoteModel> = notesList
}