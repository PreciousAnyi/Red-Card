package ps.room.redcard

import android.app.Dialog
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kyanogen.signatureview.SignatureView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ps.room.redcard.api.IssueCardApi
import ps.room.redcard.api.LoginApi
import ps.room.redcard.data.Card
import ps.room.redcard.data.IssueCard
import ps.room.redcard.data.issueCardResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

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

    private lateinit var  personnelSignature: Bitmap
    private lateinit var chiefSignature: Bitmap
    private lateinit var examOffSignature: Bitmap
    private lateinit var spsignfile: File
    private lateinit var chiefsignfile: File
    private lateinit var examOffsignfile: File
//    val file = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "spsign.png")

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

            personnelSignature = signatureView.signatureBitmap


            spsignfile = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "spsign.png")
            val stream = FileOutputStream(spsignfile)
            personnelSignature.compress(Bitmap.CompressFormat.PNG,100,stream)
            stream.close()
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
            chiefSignature = signatureView2.signatureBitmap
            chiefsignfile = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "chiefsign.png")
            val stream = FileOutputStream(chiefsignfile)
            chiefSignature.compress(Bitmap.CompressFormat.PNG,100,stream)
            stream.close()
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
            examOffSignature = signatureView3.signatureBitmap
            examOffsignfile = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "examOffsign.png")
            val stream = FileOutputStream(examOffsignfile)
            examOffSignature.compress(Bitmap.CompressFormat.PNG,100,stream)
            stream.close()
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val issueBtn = view.findViewById<View>(R.id.issuebutton)
        issueBtn.setOnClickListener {
//            Toast.makeText(context, "works", Toast.LENGTH_SHORT).show()

            val studentRegNoTv = view.findViewById<View>(R.id.StudentRegNoEdit) as TextInputEditText
            val invigilatorSpNoTv = view.findViewById<View>(R.id.InvigilatorSpNoEdit) as TextInputEditText
            val chiefInvigilatorSpNoTv = view.findViewById<View>(R.id.ChiefInvigilatorSpNoEdit) as TextInputEditText
            val examOfficerSpNoTv = view.findViewById<View>(R.id.ExamOfficerSpNoEdit) as TextInputEditText

            val studentRegNo = studentRegNoTv.text.toString()
            val invigilatorSpNo = invigilatorSpNoTv.text.toString()
            val chiefSpNo = chiefInvigilatorSpNoTv.text.toString()
            val examOfficerSpNo = examOfficerSpNoTv.text.toString()

            val offensePts = arguments?.getString("offense")
            val offenseHead = arguments?.getString("heading")
//            val card = Card(offenseHead!!, offensePts!!, studentRegNo)
//            val isshCard= IssueCard(card, invigilatorSpNo)
//            val cardjson = Gson().toJson(card)

//            Toast.makeText(context, offensePts + " ::: " + offenseHead + "  ::: " + studentRegNo+ " :: " + invigilatorSpNo, Toast.LENGTH_LONG).show()
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://red-card-backend.onrender.com/")
                .build()
                .create(IssueCardApi::class.java)
            val spsignReqFile = RequestBody.create(MediaType.parse("image/png"), spsignfile)
            val spsign = MultipartBody.Part.createFormData("spsign", spsignfile.name, spsignReqFile)
            val chiefsignReqFile = RequestBody.create(MediaType.parse("image/png"), chiefsignfile)
            val chiefsign = MultipartBody.Part.createFormData("chiefsign", chiefsignfile.name, chiefsignReqFile)
            val examOffsignReqFile = RequestBody.create(MediaType.parse("image/png"), examOffsignfile)
            val examOffsign = MultipartBody.Part.createFormData("examOffsign", examOffsignfile.name, examOffsignReqFile)
            val SpNo = RequestBody.create(MediaType.parse("text/plain"), invigilatorSpNo)
            val points = RequestBody.create(MediaType.parse("text/plain"), offensePts)
            val offense = RequestBody.create(MediaType.parse("text/plain"), offenseHead)
            val stuRegNo = RequestBody.create(MediaType.parse("text/plain"), studentRegNo)
            val chiefSp = RequestBody.create(MediaType.parse("text/plain"), chiefSpNo)
            val examOffSp = RequestBody.create(MediaType.parse("text/plain"), examOfficerSpNo)


            val retrofitData = retrofitBuilder.issueCard(SpNo, points, offense, stuRegNo, chiefSp, examOffSp, spsign, chiefsign, examOffsign)
                .enqueue(object : Callback<issueCardResponse>{
                    override fun onResponse(call: Call<issueCardResponse>, response: Response<issueCardResponse>) {
                        Toast.makeText(context, response.body()?.success.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<issueCardResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
        val offenceTv = view.findViewById<TextView>(R.id.offenseTV)
        offenceTv.text = arguments?.getString("heading")

    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            offense = it?.getString(Offense).toString()
            offensepoint = it?.getString(OffensePoint).toString()

        }
    }*/

}