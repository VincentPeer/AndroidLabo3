package ch.heigvd.daa_labo3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule

class NoteViewModel(private val repository: Repository) : ViewModel() {

    val allNotes = repository.allNotesAndSchedule
    //val notesCount = repository.notesCount

    fun createNote(note: NoteAndSchedule) {
        repository.insertNote(note)
    }


    fun generateANote() { /* création d’une Note aléatoire et insertion
dans base de données */ }
    fun deleteAllNote() { repository.deleteAll() }


    class NoteViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
