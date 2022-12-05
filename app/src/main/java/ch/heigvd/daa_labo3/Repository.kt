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
            val ownerId = notesDAO.insertNote(note.note)
            note.schedule?.let {
                it.ownerId = ownerId
                notesDAO.insertSchedule(it)
            }
        }
    }

    fun generateANote() {
        insertNote(NoteAndSchedule(Note.generateRandomNote(), Note.generateRandomSchedule()))
//        var note = Note.generateRandomNote()
//        //var schedule = Schedule(5, Calendar.getInstance().set(1,1,2000))
//        var n = NoteAndSchedule(note, null)
//        insertNote(n)
    }

    fun deleteAll() {
        thread {
            notesDAO.deleteAll()
        }
    }
}