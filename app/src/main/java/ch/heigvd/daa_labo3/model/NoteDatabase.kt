package ch.heigvd.daa_labo3.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ch.heigvd.daa_labo3.model.entities.Note
import ch.heigvd.daa_labo3.model.entities.Schedule
import kotlin.concurrent.thread

@Database(
    entities = [Note::class, Schedule::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(CalendarConverter::class)
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
                    .addCallback(CreationCallBack())
                    .build()
                INSTANCE!!
            }
        }
    }

    private class CreationCallBack : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            // onCreate is called only the first time the app is run on the device
            // or if we clear its storage

            super.onCreate(db)
            INSTANCE?.let { database ->
                thread {
                    // when the database is created for the 1st time, we populate it
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
