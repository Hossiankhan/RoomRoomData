package com.nnetwork.roomdatabase2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nnetwork.roomdatabase2.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), NoteAdapter.NoteEditListener, NoteAdapter.NoteDeleteListener {


    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        NoteDataBase.getDB(requireContext()).getNoteDao().getAllNote().let {
            var adapter: NoteAdapter = NoteAdapter(this, this)
            adapter.submitList(it)

            binding.recyclerView.adapter = adapter

        }


        binding.addNote.setOnClickListener {
//            var notes=NoteDataBase.getDB(requireContext()).getNoteDao().getNoteById(listOf<Int>(1))
//            Log.i("TAG","note:${notes[0]}")

            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }


        return binding.root
    }

    override fun onNoteEdit(note: note) {

        val bundle = Bundle()
        bundle.putInt(AddNoteFragment.NOTE_ID, note.noteid1)

        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

    }

    override fun onNoteDelete(note: note) {

    }

}