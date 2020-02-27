package com.example.notetakingapp.repository

import com.example.notetakingapp.data.NoteModel

interface IDataRepository {
    fun addNote(note: NoteModel?)
    fun getNotes(): List<NoteModel>
}