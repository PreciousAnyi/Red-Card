package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class ListOfOffenseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_offense, container, false)

//        val tv = view.findViewById<View>(R.id.loginWelcomeTV) as TextView
        val invigilatorSpNo = arguments?.getString("invigilatorSpNo")
        val studentRegNo = arguments?.getString("studentRegNo")

        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()
        Toast.makeText(context, invigilatorSpNo + " :::: " + studentRegNo, Toast.LENGTH_LONG).show()

        // Inflate the layout for this fragment
        return view
    }

}