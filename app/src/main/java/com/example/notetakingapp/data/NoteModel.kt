package com.example.notetakingapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteModel(
    var title: String? = null,
    var content: String? = null,
    var timeStamp: Long? = null
) : Parcelable