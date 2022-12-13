package ps.room.redcard

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.kyanogen.signatureview.SignatureView

class IssueACardFragment : Fragment() {

    companion object {
        fun newInstance() = IssueACardFragment()
            /*const val Offense = "offenseID"
            const val OffensePoint = "offensepoint"*/

    }

    private lateinit var viewModel: IssueACardViewModel
    private lateinit var dialog : Dialog
    private lateinit var signButton1 : Button
    private lateinit var signButton2 : Button
    private lateinit var signButton3 : Button
    private lateinit var signSignature : MaterialButton
    private lateinit var signatureView: SignatureView
    private lateinit var cancelIcon: ImageView
    private lateinit var deleteIcon: ImageView

    private lateinit var dialog2 : Dialog
    private lateinit var signSignature2 : MaterialButton
    private lateinit var signatureView2: SignatureView
    private lateinit var cancelIcon2: ImageView
    private lateinit var deleteIcon2: ImageView

    private lateinit var dialog3 : Dialog
    private lateinit var signSignature3 : MaterialButton
    private lateinit var signatureView3: SignatureView
    private lateinit var cancelIcon3: ImageView
    private lateinit var deleteIcon3: ImageView

    private lateinit var offense: String
    private lateinit var offensepoint: String
    private lateinit var offenseTV: TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_issue_a_card, container, false)

        signButton1 = view.findViewById(R.id.Signbutton)
        signButton2 = view.findViewById(R.id.ChiefInvigilatorSignbutton)
        signButton3 = view.findViewById(R.id.ExamOfficerSignbutton)
        offenseTV = view.findViewById(R.id.offenseTV)

        /*offenseTV.text = offense*/




        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.signature_pad)
        dialog.setCancelable(false)
        signatureView = dialog.findViewById(R.id.signature_view)
        signSignature = dialog.findViewById(R.id.signMB)
        cancelIcon = dialog.findViewById(R.id.cancel_signature)
        deleteIcon = dialog.findViewById(R.id.delete_signature)

        dialog2 = Dialog(requireContext())
        dialog2.setContentView(R.layout.signature_pad_2)
        dialog2.setCancelable(false)
        signatureView2 = dialog2.findViewById(R.id.signature_view2)
        signSignature2 = dialog2.findViewById(R.id.signMB2)
        cancelIcon2 = dialog2.findViewById(R.id.cancel_signature2)
        deleteIcon2 = dialog2.findViewById(R.id.delete_signature2)

        dialog3 = Dialog(requireContext())
        dialog3.setContentView(R.layout.signature_pad3)
        dialog3.setCancelable(false)
        signatureView3 = dialog3.findViewById(R.id.signature_view3)
        signSignature3 = dialog3.findViewById(R.id.signMB3)
        cancelIcon3 = dialog3.findViewById(R.id.cancel_signature3)
        deleteIcon3 = dialog3.findViewById(R.id.delete_signature3)

        signSignature.setOnClickListener {
            // TODO: There should be a way to save signatures before dismissing
            dialog.dismiss()
        }
        cancelIcon.setOnClickListener {
            dialog.dismiss()
        }
        deleteIcon.setOnClickListener {
           signatureView.clearCanvas()
        }
        //for second signature pad
        signSignature2.setOnClickListener {
            // TODO: There should be a way to save signatures before dismissing
            dialog2.dismiss()
        }
        cancelIcon2.setOnClickListener {
            dialog2.dismiss()
        }
        deleteIcon2.setOnClickListener {
            signatureView2.clearCanvas()
        }
        //for third signature pad
        signSignature3.setOnClickListener {
            // TODO: There should be a way to save signatures before dismissing
            dialog3.dismiss()
        }
        cancelIcon3.setOnClickListener {
            dialog3.dismiss()
        }
        deleteIcon3.setOnClickListener {
            signatureView3.clearCanvas()
        }

        signButton1.setOnClickListener {
            dialog.show()

        }
        signButton2.setOnClickListener {
            dialog2.show()

        }
        signButton3.setOnClickListener {
            dialog3.show()

        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IssueACardViewModel::class.java)
        // TODO: Use the ViewModel
    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            offense = it?.getString(Offense).toString()
            offensepoint = it?.getString(OffensePoint).toString()

        }
    }*/

}