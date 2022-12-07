package ch.heigvd.daa_labo3

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val prefs : SharedPreferences = getPreferences(Context.MODE_PRIVATE)
    private val viewModel:NoteViewModel by viewModels {
        NoteViewModel.NoteViewModelFactory((application as NoteApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sortedValue : String? = prefs.getString("sorted_choice", "CreationDate")
        viewModel.sorting.postValue(NoteViewModel.Sorting.valueOf(sortedValue!!))
    }

    // Add menu options to the action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.generate -> {
                viewModel.generateANote()
                true
            }
            R.id.delete_all -> {
                viewModel.deleteAllNote()
                true
            }
            R.id.creation_date -> {
                viewModel.sorting.postValue(NoteViewModel.Sorting.CreationDate)
                prefs.edit().putString("sorted_choice", NoteViewModel.Sorting.CreationDate.toString()).apply()
                true
            }
            R.id.eta -> {
                viewModel.sorting.postValue(NoteViewModel.Sorting.Schedule)
                prefs.edit().putString("sorted_choice", NoteViewModel.Sorting.Schedule.toString()).apply()
                true
            }
            else -> {
                false
            }
        }
    }
}
