package ch.heigvd.daa_labo3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ch.heigvd.daa_labo3.models.Note
import ch.heigvd.daa_labo3.models.NoteAndSchedule
import ch.heigvd.daa_labo3.models.Schedule

@Dao
interface NotesDAO {

    @Insert
    fun insertNote(note: Note) : Long

    @Insert
    fun insertSchedule(schedule: Schedule)

//    @Query("SELECT * FROM Note WHERE name = :nameToSearch ORDER BY birthday ASC")
//    fun getAllPersonsByName(nameToSearch : String) : LiveData<List<Person>>

//    @Query("SELECT * FROM Person WHERE name IN (:namesToSearch)")
//    fun getAllPersonsByName(namesToSearch: List<String>) : LiveData<List<Person>> @Query("DELETE FROM Person")

    @Transaction
    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<NoteAndSchedule>>

    @Query("DELETE FROM Note")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM Note")
    fun getCount() : LiveData<Long>

}