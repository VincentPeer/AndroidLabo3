package ch.heigvd.daa_labo3

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApp : Application() {
    // From slides, don't know what it's for
    //private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy{ NoteDatabase.getDatabase(this)}
    val repository by lazy {

        Repository(database.noteDAO())
    }
}
