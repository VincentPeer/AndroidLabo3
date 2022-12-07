package ch.heigvd.daa_labo3.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ch.heigvd.daa_labo3.R
import ch.heigvd.daa_labo3.model.entities.NoteAndSchedule
import ch.heigvd.daa_labo3.model.entities.State
import ch.heigvd.daa_labo3.model.entities.Type
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(_items: List<NoteAndSchedule> = listOf()) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    var items = listOf<NoteAndSchedule>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            val noteDiffCallback = NoteDiffCallback(items, value)
            field = value
            DiffUtil.calculateDiff(noteDiffCallback).dispatchUpdatesTo(this)
        }

    init {
        items = _items
    }

    fun sortByCreationDate() {
        items = items.sortedBy { it.note.creationDate }
    }

    fun sortBySchedule() {
        items = items.sortedWith(compareBy(
            {it.schedule == null},
            {it.schedule?.date},
        ))
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item.schedule == null)
            NO_SCHEDULE
        else
            SCHEDULE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == SCHEDULE) {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_note_schedule, parent, false), viewType
            )
        } else {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_note, parent, false),
                viewType
            )
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
        private val noteSchedulePictogram =
            view.findViewById<ImageView>(R.id.note_schedule_pictogram)
        private val noteSchesuleText = view.findViewById<TextView>(R.id.schedule_text)
        private val context = view.context

        fun bind(note: NoteAndSchedule) {
            val dateStr = SimpleDateFormat(
                context.getString(R.string.date_format),
                Locale.getDefault()
            ).format(note.note.creationDate.timeInMillis).toString()
            noteTitle.text = note.note.title
            noteTitle.text =
                context.getString(R.string.note_title_format, note.note.title, dateStr)
            noteDescr.text = note.note.text
            noteTypePictogram.setImageResource(getDrawableId(note.note.type))

            noteTypePictogram.setColorFilter(
                ContextCompat.getColor(
                    context,
                    when (note.note.state) {
                        State.DONE -> R.color.green
                        State.IN_PROGRESS -> R.color.black
                    }
                )
            )

            if (viewType == SCHEDULE && note.schedule?.date != null) {

                val date = note.schedule.date
                val remaining = date.timeInMillis - Calendar.getInstance().timeInMillis

                if (remaining < 0) {
                    val color = ContextCompat.getColor(context, R.color.red)
                    noteSchedulePictogram.setColorFilter(color)
                    noteSchesuleText.text = context.getString(R.string.schedule_late_text)
                } else {
                    val color = ContextCompat.getColor(context, R.color.black)
                    noteSchedulePictogram.setColorFilter(color)
                    val dnb = (remaining / (24 * 60 * 60 * 1000)).toInt()
                    if (dnb > 30) {
                        val mnb = (dnb / 30)
                        noteSchesuleText.text = context.resources.getQuantityString(
                            R.plurals.nb_month,
                            mnb, mnb
                        )
                    } else {
                        noteSchesuleText.text = context.resources.getQuantityString(
                            R.plurals.nb_day,
                            dnb, dnb
                        )
                    }
                }

            }
        }


    }

    companion object {

        private val SCHEDULE = 1
        private val NO_SCHEDULE = 0

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

}