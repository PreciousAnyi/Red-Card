package ps.room.redcard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText

class IssueACardFragment : Fragment() {

    companion object {
        fun newInstance() = IssueACardFragment()
    }

    private lateinit var viewModel: IssueACardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_issue_a_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IssueACardViewModel::class.java)
        // TODO: Use the ViewModel

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val issueBtn = view.findViewById<View>(R.id.issuebutton)
        issueBtn.setOnClickListener {
//            Toast.makeText(context, "works", Toast.LENGTH_SHORT).show()

            val studentRegNoTv = view.findViewById<View>(R.id.StudentRegNoEdit) as TextInputEditText
            val invigilatorSpNoTv = view.findViewById<View>(R.id.InvigilatorSpNoEdit) as TextInputEditText
            val studentRegNo = studentRegNoTv.text.toString()
            val invigilatorSpNo = invigilatorSpNoTv.text.toString()


            val mBundle = Bundle()
            mBundle.putString("invigilatorSpNo", invigilatorSpNo)
            mBundle.putString("studentRegNo", studentRegNo)
            Toast.makeText(context, studentRegNo +" : "+ invigilatorSpNo, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_issueACardFragment_to_listOfOffenseFragment, mBundle)

        }
    }

}