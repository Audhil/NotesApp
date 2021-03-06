package com.example.notetakingapp.ui.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.ActivityMainBinding
import com.example.notetakingapp.ui.base.BaseLifeCycleActivity
import com.example.notetakingapp.ui.create.CreateActivity
import com.example.notetakingapp.ui.detail.DetailActivity
import com.example.notetakingapp.util.AppError
import com.example.notetakingapp.util.showToast

class MainActivity :
    BaseLifeCycleActivity<ActivityMainBinding, MainViewModel>() {

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

    override fun onResume() {
        super.onResume()
        viewModel.triggerLiveData.value = true
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
            listAdapter.clickCallBack = { viewWrapper ->
                startActivity(DetailActivity.newInstance(viewWrapper.noteModel))
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE ->
                            viewDataBinding.addFab.animate().scaleX(1f).scaleY(1f).setDuration(100).start()

                        else ->
                            viewDataBinding.addFab.animate().scaleX(0f).scaleY(0f).setDuration(100).start()
                    }
                }
            })
        }

    private fun setUpFab() =
        viewDataBinding.addFab.setOnClickListener {
            startActivity(CreateActivity.newIntent())
        }
}
