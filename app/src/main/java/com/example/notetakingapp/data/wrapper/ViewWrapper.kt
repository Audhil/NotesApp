package com.example.notetakingapp.data.wrapper

import android.widget.TextView
import com.example.notetakingapp.data.NoteModel

data class ViewWrapper(
    val noteModel: NoteModel,
    val titleTxtView: TextView,
    val contentTxtView: TextView
)