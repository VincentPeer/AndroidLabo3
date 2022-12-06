package ch.heigvd.daa_labo3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.Schedule
import kotlin.concurrent.thread

@Database(
    entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Note.CalendarConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDAO(): NotesDAO

    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "notes.db"
                )
                    .fallbackToDestructiveMigration()
                    // not called thus useless. Don't know wy...
                    .addCallback(CreationCallBack())
                    .build()
                INSTANCE!!
            }
        }
    }

    // Not called, don't know wy...
    private class CreationCallBack : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                val isEmpty = database.noteDAO().getCount().value == 0L
                if (isEmpty) {
                    thread {
                        // when the database is created for the 1st time, we can, for example, populate it
                        // should be done asynchronously
                        val dao = database.noteDAO()
                        for (i in 0..10) {
                            val id = dao.insertNote(Note.generateRandomNote())
                            Note.generateRandomSchedule()?.let {
                                it.ownerId = id
                                dao.insertSchedule(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
