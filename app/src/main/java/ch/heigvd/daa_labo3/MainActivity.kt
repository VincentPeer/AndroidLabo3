package ch.heigvd.daa_labo3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val viewModel:NoteViewModel by viewModels {
        NoteViewModel.NoteViewModelFactory((application as NoteApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                true
            }
            R.id.eta -> {
                viewModel.sorting.postValue(NoteViewModel.Sorting.Schedule)
                true
            }
            else -> {
                false
            }
        }
    }
}
