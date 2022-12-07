package ch.heigvd.daa_labo3.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ch.heigvd.daa_labo3.model.entities.Note
import ch.heigvd.daa_labo3.model.entities.NoteAndSchedule
import ch.heigvd.daa_labo3.model.entities.Schedule

@Dao
interface NotesDAO {

    @Insert
    fun insertNote(note: Note) : Long

    @Insert
    fun insertSchedule(schedule: Schedule): Long

    @Transaction
    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<NoteAndSchedule>>

    @Query("DELETE FROM Note")
    fun deleteAllNotes()

    @Query("DELETE FROM Schedule")
    fun deleteAllSchedules()

    @Query("SELECT COUNT(*) FROM Note")
    fun getCount() : LiveData<Long>

}
