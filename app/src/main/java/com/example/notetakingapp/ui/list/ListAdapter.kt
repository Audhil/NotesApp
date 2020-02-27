package com.example.notetakingapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.data.NoteModel
import com.example.notetakingapp.data.wrapper.ViewWrapper
import com.example.notetakingapp.databinding.EmptyListItemBinding
import com.example.notetakingapp.databinding.NoteListItemBinding
import com.example.notetakingapp.util.CallBack
import com.example.notetakingapp.util.ConstantsUtil

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypes by lazy {
        arrayOf(1, 2)
    }

    private val items by lazy {
        arrayListOf<NoteModel>()
    }

    var clickCallBack: CallBack<ViewWrapper>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            viewTypes[0] ->
                EmptyViewHolder(
                    EmptyListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            viewTypes[1] ->
                ListViewHolder(NoteListItemBinding.inflate(LayoutInflater.from(parent.context)))

            else ->
                EmptyViewHolder(
                    EmptyListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }


    override fun getItemCount(): Int =
        if (items.isEmpty())
            ConstantsUtil.ITEM_COUNT_1
        else
            items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is ListViewHolder ->
                holder.bindTo(position, items[position], clickCallBack)

            else ->
                Unit
        }

    override fun getItemViewType(position: Int): Int =
        if (items.isEmpty())
            viewTypes[0]
        else
            viewTypes[1]


    fun addNotes(it: List<NoteModel>) {
        with(items) {
            clear()
            addAll(it)
        }
        notifyDataSetChanged()
    }

    private class ListViewHolder(
        private val itemBinding: NoteListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindTo(
            pos: Int,
            noteModel: NoteModel,
            clickCallBack: CallBack<ViewWrapper>?
        ) =
            itemBinding.run {
                note = noteModel
                executePendingBindings()
                root.setOnClickListener {
                    clickCallBack?.invoke(
                        ViewWrapper(
                            noteModel,
                            itemBinding.noteTitle,
                            itemBinding.noteContent
                        )
                    )
                }
            }
    }

    private class EmptyViewHolder(
        emptyListItemBinding: EmptyListItemBinding
    ) : RecyclerView.ViewHolder(emptyListItemBinding.root)
}