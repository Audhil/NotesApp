package com.example.notetakingapp.ui.list

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.ActivityMainBinding
import com.example.notetakingapp.ui.base.BaseLifeCycleActivity
import com.example.notetakingapp.util.AppError
import com.example.notetakingapp.util.showToast

class MainActivity : BaseLifeCycleActivity<ActivityMainBinding, MainViewModel>() {

    override fun getBindingVariable(): Int = 0

    override fun initErrorObserver() {
        viewModel.errorLiveData.observe(this, Observer {
            when (it) {
                AppError.UNKNOWN,
                AppError.SOMETHING_WENT_WRONG,
                AppError.NOTE_NOT_AVAILABLE ->
                    getString(R.string.something_went_wrong).showToast()

                else ->
                    Unit
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataObservers()
        setUpRecyclerView()
        setUpFab()
    }

    private fun setUpDataObservers() =
        viewModel.notesLiveData.observe(this, Observer {
            listAdapter.addNotes(it)
        })

    private val listAdapter by lazy {
        ListAdapter()
    }

    private fun setUpRecyclerView() =
        viewDataBinding.notesRecyclerview.run {
            adapter = listAdapter
            listAdapter.clickCallBack = {
                println("clicked item: $it")
            }
        }

    private fun setUpFab() {
        viewDataBinding.addFab.setOnClickListener {
            "fab clicked".showToast()
        }
    }
}
