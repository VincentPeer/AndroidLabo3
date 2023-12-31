package ch.heigvd.daa_labo3.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import ch.heigvd.daa_labo3.NoteApp
import ch.heigvd.daa_labo3.NoteViewModel
import ch.heigvd.daa_labo3.R

class MainActivity : AppCompatActivity() {
    private val viewModel: NoteViewModel by viewModels {
        NoteViewModel.NoteViewModelFactory((application as NoteApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        // Retrieve the sort type set by the user during his last activity or set creation date by default
        val sortedValue : String? = getPreferences(Context.MODE_PRIVATE).getString("sorted_choice", "CreationDate")
        viewModel.sorting.postValue(NoteViewModel.Sorting.valueOf(sortedValue!!))
        return super.onCreateView(name, context, attrs)
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
                getPreferences(Context.MODE_PRIVATE).edit().putString("sorted_choice", "CreationDate").apply()
                true
            }
            R.id.eta -> {
                viewModel.sorting.postValue(NoteViewModel.Sorting.Schedule)
                getPreferences(Context.MODE_PRIVATE).edit().putString("sorted_choice", "Schedule").apply()
                true
            }
            else -> {
                false
            }
        }
    }
}
