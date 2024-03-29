package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView


class InvigilatorFragment : Fragment() {

    private lateinit var issueACard: MaterialCardView
    private lateinit var savedImages: MaterialCardView
    private lateinit var attendance: MaterialCardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_invigilator, container, false)

        issueACard = view.findViewById(R.id.issueACardCV)
        savedImages = view.findViewById(R.id.savedImagesCV)
        attendance = view.findViewById(R.id.attendanceCV)
        val personnelNo = arguments?.getString("personnelNo")
        val password = arguments?.getString("password")
//        Toast.makeText(context, arguments?.getString("personnelNo"), Toast.LENGTH_LONG).show()
//        Toast.makeText(context, arguments?.getString("personnelNo"), Toast.LENGTH_LONG).show()
//        Toast.makeText(context, arguments?.getString("personnelNo"), Toast.LENGTH_LONG).show()
        val mBundle = Bundle()
        mBundle.putString("personnelNo", personnelNo)
        mBundle.putString("password", password)
        issueACard.setOnClickListener {
            findNavController().navigate(R.id.action_invigilatorFragment_to_listOfOffenseFragment)
        }
        attendance.setOnClickListener {
            findNavController().navigate(R.id.action_invigilatorFragment_to_captureAttendanceFragment, mBundle)
        }
        savedImages.setOnClickListener {
            findNavController().navigate(R.id.action_invigilatorFragment_to_savedImagesFragment, mBundle)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val issueACard = view.findViewById<View>(R.id.issueACardCV)
//        issueACard.setOnClickListener {
//            findNavController().navigate(R.id.action_invigilatorFragment_to_issueACardFragment)
//        }
     }

}