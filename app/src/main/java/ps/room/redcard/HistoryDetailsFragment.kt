package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HistoryDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val points = arguments?.getString("points")
        val offense = arguments?.getString("offence")
        val studentRegNo = arguments?.getString("studentRegNo")
        val personnelNo = arguments?.getString("personnelNo")

        val pointsTv: TextView = view.findViewById(R.id.historyDetailsOffencePointsTV)
        val offenseTv: TextView = view.findViewById(R.id.historyDetailsOffenseTV)
        val studentRegTv: TextView = view.findViewById(R.id.historyDetailsStudentRegNoTV)
        val personnelTv: TextView = view.findViewById(R.id.historyDetailsInvigilatorPersonnelNoTV)

        pointsTv.text = points
        offenseTv.text = offense
        studentRegTv.text = studentRegNo
        personnelTv.text = personnelNo
    }


}