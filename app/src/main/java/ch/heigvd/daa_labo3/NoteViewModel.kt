package ch.heigvd.daa_labo3

import androidx.lifecycle.*
import ch.heigvd.daa_labo3.model.Repository

class NoteViewModel(private val repository: Repository) : ViewModel() {

    val allNotes = repository.allNotesAndSchedule

    val notesCount = repository.notesCount
    val sorting = MutableLiveData<Sorting>(Sorting.CreationDate)

    fun generateANote() {
        repository.generateANote()
    }

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

    enum class Sorting {
        Schedule,
        CreationDate
    }
}
