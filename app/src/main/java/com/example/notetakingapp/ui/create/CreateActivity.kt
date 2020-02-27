package com.example.notetakingapp.ui.create

import android.content.Intent
import com.example.notetakingapp.NotesApplication
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.ActivityCreateBinding
import com.example.notetakingapp.ui.base.BaseLifeCycleActivity

class CreateActivity :
    BaseLifeCycleActivity<ActivityCreateBinding, CreateViewModel>() {

    override fun getBindingVariable(): Int = 0

    override fun initErrorObserver() {
    }

    override fun getLayoutId(): Int = R.layout.activity_create

    override val viewModelClass: Class<CreateViewModel> = CreateViewModel::class.java

    companion object {
        fun newIntent(): Intent = Intent(NotesApplication.INSTANCE, CreateActivity::class.java)
    }
}