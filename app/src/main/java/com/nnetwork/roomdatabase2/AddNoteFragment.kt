package com.nnetwork.roomdatabase2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import com.nnetwork.roomdatabase2.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentAddNoteBinding

    val spinnerlist = listOf("Select your Priority", "High", "Medium", "Low")


    private var selectedDate: String? = null
    private var time1: String? = null
    private var spinn: String? = null
   var noteid2:Int = 0


    companion object {
        var
                NOTE_ID= "note_id"
    }
    lateinit var note:note
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        noteid2 = requireArguments().getInt(NOTE_ID)

        if (noteid2 != 0) {

            note = NoteDataBase.getDB(requireContext()).getNoteDao()
                .getNoteById(listOf<Int>(noteid2))[0]


            binding.apply {
                editText.setText(note.title)
                timepic.text = note.time
                datepic.text = note.date

            }

//
        }


        var spinnerAdpter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinnerlist)

        binding.spinner.adapter = spinnerAdpter

        binding.spinner.onItemSelectedListener = this@AddNoteFragment



        binding.datepic.setOnClickListener {
            pickDate()
        }

        binding.timepic.setOnClickListener {
            pickTime()
        }

        binding.submitbtn.setOnClickListener {
            var titleStr = binding.editText.text.toString()
            var timeStr = time1 ?: "00:00"
            var dataStr = selectedDate ?: "0/0/00"
            var spinnerStr = spinn ?: spinnerlist[3]

            var Note = note(title = titleStr, time = timeStr, date = dataStr, spinn = spinnerStr)

            if (noteid2 == 0) {
                NoteDataBase.getDB(requireContext()).getNoteDao().insertData(note)
            } else {
                note.noteid1 = noteid2
               NoteDataBase.getDB(requireContext()).getNoteDao().updateNote(note)
            }
        }

        return binding.root
    }

    private fun pickTime() {

        val time = Calendar.getInstance()
        val hour = time.get(Calendar.HOUR_OF_DAY)
        val minute = time.get(Calendar.MINUTE)


        val timePickerDialog = TimePickerDialog(
            requireContext(), { _, hour, minute ->

                time1 = "$hour:$minute"
                binding.timepic.text = time1

            }, hour, minute, false
        )
        timePickerDialog.show()

    }


    private fun pickDate() {
        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val dataPickerDialog = DatePickerDialog(
            requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                selectedDate = "$dayOfMonth/$month/$year"
                binding.datepic.text = selectedDate

            }, year, month, date
        )
        dataPickerDialog.show()

    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        spinn = spinnerlist[position]

        val text = binding.text1
        when (spinn) {
            spinnerlist[0] -> text.setTextColor(Color.BLACK)
            spinnerlist[1] -> text.setTextColor(Color.GREEN)
            spinnerlist[2] -> text.setTextColor(Color.YELLOW)
            spinnerlist[3] -> text.setTextColor(Color.RED)
        }

        Toast.makeText(requireContext(), spinn, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}
