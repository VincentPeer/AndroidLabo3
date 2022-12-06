package ch.heigvd.daa_labo3

import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule
import kotlin.concurrent.thread

class Repository(private val notesDAO: NotesDAO) {

    val allNotesAndSchedule = notesDAO.getAll()
    val notesCount = notesDAO.getCount()

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
    }

    fun deleteAll() {
        thread {
            notesDAO.deleteAll()
        }
    }
}