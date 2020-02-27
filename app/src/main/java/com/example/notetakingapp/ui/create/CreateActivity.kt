package com.example.notetakingapp.ui.create

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import com.example.notetakingapp.NotesApplication
import com.example.notetakingapp.R
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.databinding.ActivityCreateBinding
import com.example.notetakingapp.ui.base.BaseLifeCycleActivity
import com.example.notetakingapp.ui.detail.DetailActivity
import com.example.notetakingapp.util.AppError
import com.example.notetakingapp.util.addTextWatcher
import com.example.notetakingapp.util.showToast

class CreateActivity :
    BaseLifeCycleActivity<ActivityCreateBinding, CreateViewModel>() {

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

    override fun getLayoutId(): Int = R.layout.activity_create

    override val viewModelClass: Class<CreateViewModel> = CreateViewModel::class.java

    companion object {
        fun newIntent(): Intent = Intent(NotesApplication.INSTANCE, CreateActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetUp()
    }

    private fun initialSetUp() =
        with(viewDataBinding) {
            titleEditText.addTextWatcher {
                setFabVisibility()
            }

            noteEditText.addTextWatcher {
                setFabVisibility()
            }

            saveFab.setOnClickListener {
                val noteModel = NoteModel(
                    title = titleEditText.text.toString(),
                    content = noteEditText.text.toString(),
                    timeStamp = System.currentTimeMillis()
                )
                viewModel.addNote(note = noteModel)
                startActivity(DetailActivity.newInstance(noteModel))
                finish()
            }
        }

    private fun setFabVisibility() =
        with(viewDataBinding) {
            saveFab.visibility =
                if (TextUtils.isEmpty(titleEditText.text) || TextUtils.isEmpty(noteEditText.text))
                    View.GONE
                else
                    View.VISIBLE
        }
}