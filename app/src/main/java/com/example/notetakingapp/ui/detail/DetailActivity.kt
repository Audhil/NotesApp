package com.example.notetakingapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.databinding.DataBindingUtil
import com.example.notetakingapp.NotesApplication
import com.example.notetakingapp.R
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.databinding.ActivityDetailBinding
import com.example.notetakingapp.ui.base.BaseActivity
import com.example.notetakingapp.util.ConstantsUtil

class DetailActivity : BaseActivity() {

    companion object {
        fun newInstance(parcelable: Parcelable? = null): Intent =
            Intent(NotesApplication.INSTANCE, DetailActivity::class.java).apply {
                parcelable?.run {
                    val bundle = Bundle()
                    bundle.putParcelable(ConstantsUtil.PARCELABLE, parcelable)
                    putExtras(bundle)
                }
            }
    }

    lateinit var binding: ActivityDetailBinding

    private val noteContentText by lazy {
        intent.extras?.getParcelable<NoteModel>(ConstantsUtil.PARCELABLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.run {
            note = noteContentText
            executePendingBindings()
        }
    }
}