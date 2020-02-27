package com.example.notetakingapp.repository

import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.util.AppError
import com.example.notetakingapp.util.ErrorLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository
@Inject
constructor(
    val errorLiveData: ErrorLiveData
) : IDataRepository {

    private val fakeData by lazy {
        arrayListOf<NoteModel>(
            NoteModel(
                "Title 1",
                "Show the note title and content, along with the timestamp when the note was created (ex: ’13 January 2018, 5:30 PM’). This screen does not have any interaction and just displays static content."
            ),
            NoteModel(
                "2nd Title",
                "The only validation on these fields is that they must not be blank. The user must be able to enter values for both these fields. Hitting the “Save” button should save the note and take the user to screen 3."
            ),
            NoteModel(
                "Teen title hai",
                "This is the home screen. When it launches, list all the notes the user has created in reverse chronological order (latest created note first)." +
                        "This screen must contain an action (either a button or an action bar menu option) that opens screen 2. Tapping on a note should open screen 3."
            )
        )
    }

    private val notesList by lazy {
        arrayListOf<NoteModel>()
    }

    override fun addNote(note: NoteModel?) {
        note?.run {
            notesList.add(this)
        } ?: errorLiveData.setAppError(AppError.NOTE_NOT_AVAILABLE)
    }

    override fun getNotes(): List<NoteModel> = notesList    //  fakeData
}