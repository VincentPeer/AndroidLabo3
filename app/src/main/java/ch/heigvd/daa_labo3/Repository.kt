package ch.heigvd.daa_labo3

import androidx.lifecycle.LiveData
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule
import ch.heigvd.daa_labo3.models.Schedule
import java.util.Calendar
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

    fun generateANote() {
        var note = Note.generateRandomNote()
        var schedule = Note.generateRandomSchedule()
        var n = NoteAndSchedule(note, schedule)
        insertNote(n)
    }

    fun deleteAll() {
        thread {
            notesDAO.deleteAll()
        }
    }
}