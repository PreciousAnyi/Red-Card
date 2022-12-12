package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView


class DashboardFragment : Fragment() {

    private lateinit var examSchedule: MaterialCardView
    private lateinit var howTo: MaterialCardView
    private lateinit var history: MaterialCardView
    private lateinit var invigilator: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val tv = view.findViewById<View>(R.id.loginWelcomeTV) as TextView
        val personnelNo = arguments?.getString("personnelNo")

        tv.text = "Welcome " + personnelNo

        // Inflate the layout for this fragment
        examSchedule = view.findViewById(R.id.examScheduleCV)
        howTo = view.findViewById(R.id.howToCV)
        history = view.findViewById(R.id.historyCV)
        invigilator = view.findViewById(R.id.invigilatorCV)


        examSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_ExamSheduleFragment)
        }
        howTo.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_HowToFragment)
        }
        history.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_historyFragment)
        }
        invigilator.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_invigilatorFragment)
        }

        return view
    }


}