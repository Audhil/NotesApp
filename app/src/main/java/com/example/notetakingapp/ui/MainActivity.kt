package com.example.notetakingapp.ui

import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.ActivityMainBinding
import com.example.notetakingapp.ui.base.BaseLifeCycleActivity

class MainActivity : BaseLifeCycleActivity<ActivityMainBinding, MainViewModel>() {

    override fun getBindingVariable(): Int = 0

    override fun initErrorObserver() {
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java
}
