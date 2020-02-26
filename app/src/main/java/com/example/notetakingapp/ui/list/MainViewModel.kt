package com.example.notetakingapp.ui.list

import com.example.notetakingapp.repository.DataRepository
import com.example.notetakingapp.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel
@Inject
constructor(
    private val dataRepository: DataRepository
) : BaseViewModel()