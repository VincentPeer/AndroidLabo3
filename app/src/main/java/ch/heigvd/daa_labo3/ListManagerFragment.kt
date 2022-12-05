package ch.heigvd.daa_labo3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment



private const val ARG_COUNTER = "param_counter"


class ListManagerFragment : Fragment() {
    private var nbElements : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nbElements = it.getInt(ARG_COUNTER)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_controls, container, false)
    }
    companion object {
        @JvmStatic
        fun newInstance(counter: Int = 0) =
            ListManagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COUNTER, counter)
                }
            }
    }

}