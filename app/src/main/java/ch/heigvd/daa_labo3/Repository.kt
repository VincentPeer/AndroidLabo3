package ch.heigvd.daa_labo3

import ch.heigvd.daa_labo3.models.Note
import kotlin.concurrent.thread

class Repository(private val notesDAO: NotesDAO) {

    val allNotesAndSchedule = notesDAO.getAll()
    val notesCount  = notesDAO.getCount()

    fun insertNote(note: Note) {
        thread {
            notesDAO.insertNote(note)
        }
    }

}