package ch.heigvd.daa_labo3

import android.app.Application
import ch.heigvd.daa_labo3.model.NoteDatabase
import ch.heigvd.daa_labo3.model.Repository

class NoteApp : Application() {
    val repository by lazy {

        Repository(NoteDatabase.getDatabase(this).noteDAO())
    }
}
