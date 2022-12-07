package ch.heigvd.daa_labo3.view

import androidx.recyclerview.widget.DiffUtil
import ch.heigvd.daa_labo3.model.entities.NoteAndSchedule

class NoteDiffCallback(private val oldList: List<NoteAndSchedule>, private val newList: List<NoteAndSchedule>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].note.noteId == newList.get(newItemPosition).note.noteId
    }
    override fun areContentsTheSame(oldItemPosition : Int, newItemPosition : Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old::class == new::class &&
                old.schedule == new.schedule &&
                old.note.state == new.note.state &&
                old.note.title == new.note.title &&
                old.note.text == new.note.text &&
                old.note.creationDate == new.note.creationDate &&
                old.note.type == new.note.type

    }
}