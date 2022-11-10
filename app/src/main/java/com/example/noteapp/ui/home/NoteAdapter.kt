package com.example.noteapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.model.Note
import com.example.noteapp.databinding.CardNoteBinding

class NoteAdapter(
    private val listNote: List<Note>,
    private val onClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = CardNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val note = listNote[position]

        holder.binding.apply {
            tvTitle.text = note.title
            cardNote.setOnClickListener {
                onClick(note)
            }
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    class Holder(val binding: CardNoteBinding) : RecyclerView.ViewHolder(binding.root)
}