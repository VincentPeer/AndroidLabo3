package ch.heigvd.daa_labo3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ch.heigvd.daa_labo3.models.Note

@Dao
interface NotesDAO {

    @Insert
    fun insertAll(vararg note: Note)

//    @Query("SELECT * FROM Note WHERE name = :nameToSearch ORDER BY birthday ASC")
//    fun getAllPersonsByName(nameToSearch : String) : LiveData<List<Person>>

//    @Query("SELECT * FROM Person WHERE name IN (:namesToSearch)")
//    fun getAllPersonsByName(namesToSearch: List<String>) : LiveData<List<Person>> @Query("DELETE FROM Person")

    @Query("SELECT * FROM Note")
    fun getAll(): LiveData<List<Note>>

    @Query("DELETE FROM Note")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM Note")
    fun getCount() : LiveData<Long>

}