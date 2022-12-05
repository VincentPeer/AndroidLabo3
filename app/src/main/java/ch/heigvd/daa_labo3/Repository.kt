package ch.heigvd.daa_labo3

import androidx.lifecycle.LiveData
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule
import kotlin.concurrent.thread

class Repository(private val notesDAO: NotesDAO) {

    val allNotesAndSchedule = notesDAO.getAll()
    val notesCount  = notesDAO.getCount()


    fun insertNote(note: NoteAndSchedule) {
        thread {
            notesDAO.insertNote(note.note)
            note.schedule?.let {
                notesDAO.insertSchedule(it)
            }
        }
    }

    fun deleteAll() {
        thread {
            notesDAO.deleteAll()
        }
    }
}