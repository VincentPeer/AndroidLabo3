package ch.heigvd.daa_labo3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ch.heigvd.daa_labo3.models.Note
import kotlin.concurrent.thread

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true)
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
                    .build()
                INSTANCE!!
            }
        }
    }

    private class MyDatabaseCallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                val isEmpty = database.noteDAO().getCount().value == 0L
                if (isEmpty) {
                    thread {
                    // when the database is created for the 1st time, we can, for example, populate it
                    // should be done asynchronously
                    }
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
        }
    }
}