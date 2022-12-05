package ch.heigvd.daa_labo3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule
import ch.heigvd.daa_labo3.models.State
import ch.heigvd.daa_labo3.models.Type
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment() {

    private val noteViewModel: NoteViewModel by activityViewModels {
        NoteViewModel.NoteViewModelFactory((requireActivity().application as NoteApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val adapter = NotesListAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(view.context)

        noteViewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.items = it
        }

    }


}