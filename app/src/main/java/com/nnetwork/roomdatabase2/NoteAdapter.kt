package com.nnetwork.roomdatabase2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nnetwork.roomdatabase2.databinding.ItemNoteBinding


class NoteAdapter(
    var noteEditeListener: NoteEditListener,
    var noteDeleteListener: NoteDeleteListener
) : ListAdapter<note, NoteAdapter.NoteViewHolder>(comparator) {


    interface NoteEditListener {
        fun onNoteEdit(note: note)
    }

    interface NoteDeleteListener {
        fun onNoteDelete(note: note)
    }


    class NoteViewHolder(var binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)


    companion object {
        var comparator = object : DiffUtil.ItemCallback<note>() {
            override fun areItemsTheSame(oldItem: note, newItem: note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: note, newItem: note): Boolean {
                return oldItem.noteid1 == newItem.noteid1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {
            {
                holder.binding.apply {
                    noteTitle.text = it.title
                    noteDate.text = it.date
                    noteTime.text = it.time
                }

                holder.itemView.setOnClickListener{_ ->
                    noteEditeListener.onNoteEdit(it)
                }
                holder.binding.deleteicon.setOnClickListener {_->
                    noteDeleteListener.onNoteDelete(it)
                }

            }
        }
    }
}

