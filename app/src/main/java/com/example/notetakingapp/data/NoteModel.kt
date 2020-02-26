package com.example.notetakingapp.data

data class NoteModel(
    var title: String? = null,
    var content: String? = null,
    var timeStamp: Long? = null
)