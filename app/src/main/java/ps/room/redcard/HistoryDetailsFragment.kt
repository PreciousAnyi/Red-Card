package ps.room.redcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

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
        val offense = arguments?.getString("offense")
        val studentRegNo = arguments?.getString("studentRegNo")
        val personnelNo = arguments?.getString("personnelNo")
        val chiefSpNo = arguments?.getString("chiefSpNo")
        val examOfficerSpNo = arguments?.getString("examOfficerSpNo")
        val spsignFileName = arguments?.getString("spsignFileName")
        val chiefsignFileName = arguments?.getString("chiefsignFileName")
        val examOffSignFileName = arguments?.getString("examOffSignFileName")

        val pointsTv: TextView = view.findViewById(R.id.historyDetailsOffencePointsTV)
        val offenseTv: TextView = view.findViewById(R.id.historyDetailsOffenseTV)
        val studentRegTv: TextView = view.findViewById(R.id.historyDetailsStudentRegNoTV)
        val personnelTv: TextView = view.findViewById(R.id.historyDetailsInvigilatorPersonnelNoTV)
        val chiefSpTv: TextView = view.findViewById(R.id.historyDetailsChiefInvigilatorPersonnelNoTV)
        val examOfficerSpTv: TextView = view.findViewById(R.id.historyDetailsExamOfficerPersonnelNoTV)
        val personnelSpImageView: ImageView = view.findViewById(R.id.invigilatorSignatureimageView)
        val chiefSpImageView: ImageView = view.findViewById(R.id.chiefInvigilatorSignatureImageView)
        val examOffSpImageView: ImageView = view.findViewById(R.id.examOfficerSignatureImageView)

        pointsTv.text = points
        offenseTv.text = offense
        studentRegTv.text = studentRegNo
        personnelTv.text = personnelNo
        chiefSpTv.text = chiefSpNo
        examOfficerSpTv.text = examOfficerSpNo

        val spsignUrl = "https://red-card-backend.onrender.com/file/" + spsignFileName
        Glide.with(personnelSpImageView.context)
            .load(spsignUrl)
            .into(personnelSpImageView)
        val chiefsignUrl = "https://red-card-backend.onrender.com/file/" + chiefsignFileName
        Glide.with(chiefSpImageView.context)
            .load(chiefsignUrl)
            .into(chiefSpImageView)
        val examsignUrl = "https://red-card-backend.onrender.com/file/" + examOffSignFileName
        Glide.with(examOffSpImageView.context)
            .load(examsignUrl)
            .into(examOffSpImageView)
    }


}