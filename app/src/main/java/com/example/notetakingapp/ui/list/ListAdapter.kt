package com.example.notetakingapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.databinding.NoteListItemBinding
import com.example.notetakingapp.util.CallBack

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items by lazy {
        arrayListOf<NoteModel>()
    }

    var clickCallBack: CallBack<NoteModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ListViewHolder(NoteListItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is ListViewHolder ->
                holder.bindTo(position, items[position], clickCallBack)

            else ->
                Unit
        }

    fun addNotes(it: List<NoteModel>) {
        val diffUtil = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition].timeStamp == it[newItemPosition].timeStamp

            override fun getOldListSize(): Int =
                items.size

            override fun getNewListSize(): Int =
                it.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition] == it[newItemPosition]
        })
        items.apply {
            clear()
            addAll(it)
        }
        diffUtil.dispatchUpdatesTo(this)
    }

    private class ListViewHolder(
        private val itemBinding: NoteListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindTo(
            pos: Int,
            noteModel: NoteModel,
            clickCallBack: CallBack<NoteModel>?
        ) =
            itemBinding.run {
                note = noteModel
                executePendingBindings()
                root.setOnClickListener {
                    clickCallBack?.invoke(noteModel)
                }
            }
    }
}