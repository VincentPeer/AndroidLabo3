package ch.heigvd.daa_labo3


import androidx.lifecycle.*

class NoteViewModel(private val repository: Repository) : ViewModel() {

    val allNotes = repository.allNotesAndSchedule

    val notesCount = repository.getCount()
    val sorting = MutableLiveData<Sorting>(Sorting.CreationDate)

    fun generateANote() {
        repository.generateANote()
    }

    fun deleteAllNote() { repository.deleteAll() }

    fun getCount() {
        repository.getCount()
    }


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
