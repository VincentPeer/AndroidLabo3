package ch.heigvd.daa_labo3

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val repository by lazy {
        val database = NoteDatabase.getDatabase(this)
        Repository(database.noteDAO()/*, applicationScope*/)
    }

}