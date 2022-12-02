package ch.heigvd.daa_labo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule

class NotesListAdapter(_items : List<NoteAndSchedule> = listOf()) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    var items = listOf<NoteAndSchedule>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    init {
        items = _items
    }


    override fun getItemViewType(position: Int): Int {
        return NOTE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_note, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val noteTitle = view.findViewById<TextView>(R.id.note_title)
        private val noteDescr = view.findViewById<TextView>(R.id.note_description)
        fun bind(note: NoteAndSchedule) {
            noteTitle.text = note.note.title
            noteDescr.text = note.note.text
        }
    }

    companion object {
        private val NOTE = 1
    }

}