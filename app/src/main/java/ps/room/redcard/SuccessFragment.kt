package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class SuccessFragment : Fragment() {

    private lateinit var logIn: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_success, container, false)
        logIn = view.findViewById(R.id.successLogin)

        logIn.setOnClickListener {
            findNavController().navigate(R.id.action_successFragment_to_loginFragment)
        }

        return view
    }


}