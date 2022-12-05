package ch.heigvd.daa_labo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule
import ch.heigvd.daa_labo3.models.Type

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
        val item = items[position]
        return if (item.schedule == null)
            WITH_SCHEDULE
        else
            WITHOUT_SCHEDULE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 1) {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_note_schedule, parent, false), viewType)
        } else {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_note, parent, false),viewType)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View, private val viewType: Int) : RecyclerView.ViewHolder(view) {
        private val noteTitle = view.findViewById<TextView>(R.id.note_title)
        private val noteDescr = view.findViewById<TextView>(R.id.note_description)
        private val noteTypePictogram = view.findViewById<ImageView>(R.id.note_type_pictogram)
        private val noteSchedulePictogram = view.findViewById<ImageView>(R.id.note_schedule_pictogram)
        private val context = view.context

        fun bind(note: NoteAndSchedule) {
            noteTitle.text = note.note.title
            noteDescr.text = note.note.text
            noteTypePictogram.setImageResource(getDrawableId(note.note.type))

            if (viewType == WITH_SCHEDULE) {
                val color = ContextCompat.getColor(context, R.color.purple_200)
                noteSchedulePictogram.setColorFilter(color)
            }

        }

    }

    companion object {

        private val WITH_SCHEDULE = 1
        private val WITHOUT_SCHEDULE = 0

        fun getDrawableId(noteType: Type): Int {
            when (noteType) {
                Type.NONE -> return R.drawable.note
                Type.TODO -> return R.drawable.todo
                Type.SHOPPING -> return R.drawable.shopping
                Type.WORK -> return R.drawable.work
                Type.FAMILY -> return R.drawable.family
            }
        }

    }

    private enum class ViewType {
        WITH_SCHEDULE,
        WITHOUT_SCHEDULE
    }

}