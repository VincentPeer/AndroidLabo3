package ch.heigvd.daa_labo3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.PrimaryKey
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.State
import ch.heigvd.daa_labo3.models.Type
import java.util.*

class NoteViewModel(private val repository: Repository) : ViewModel() {

    val allNotes = repository.allNotes
    val notesCount = repository.notesCount

    fun createNote(note: Note) {
        repository.insertNotes(note)
    }


    class NoteViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                return NoteViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}