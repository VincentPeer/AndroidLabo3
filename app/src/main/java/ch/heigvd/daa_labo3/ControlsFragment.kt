package ch.heigvd.daa_labo3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ControlsFragment : Fragment() {
    private val noteViewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((requireActivity().application as NoteApp).repository)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_controls, container, false).apply {
            if (savedInstanceState == null)
                this.findViewById<TextView>(R.id.note_counter).text = (noteViewModel.notesCount ?: 0).toString()
        }
}
