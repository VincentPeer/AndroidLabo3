package ch.heigvd.daa_labo3.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ch.heigvd.daa_labo3.NoteApp
import ch.heigvd.daa_labo3.NoteViewModel
import ch.heigvd.daa_labo3.R

class ControlsFragment : Fragment() {
    private val noteViewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((requireActivity().application as NoteApp).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_controls, container, false).apply {
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the number of items in the list
        noteViewModel.notesCount.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.note_counter).text = it.toString()
        }



        // Generate a note when the generate button is pressed
        view.findViewById<Button>(R.id.generate_btn).setOnClickListener {
            noteViewModel.generateANote()
        }

        // Delete all notes when delete button is pressed
        view.findViewById<Button>(R.id.delete_btn).setOnClickListener {
            noteViewModel.deleteAllNote()
        }
    }
}
