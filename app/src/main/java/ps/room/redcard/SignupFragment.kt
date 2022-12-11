package ps.room.redcard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import ps.room.redcard.api.LoginApi
import ps.room.redcard.api.SignUpApi
import ps.room.redcard.data.SignUpResponse
import ps.room.redcard.data.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    private lateinit var viewModel: SignupViewModel
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signUpAuth = view.findViewById<View>(R.id.sign_up_auth)
        signUpAuth.setOnClickListener {
            val personnelNoTv = view.findViewById<View>(R.id.SignupPersonnelEdit) as TextInputEditText
            val personnelNo = personnelNoTv.text.toString()
            val passwordTv = view.findViewById<View>(R.id.SignupPasswordEdit) as TextInputEditText
            val password = passwordTv.text.toString()


            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://RedCard.onotuumoru.repl.co/")
                .build()
                .create(SignUpApi::class.java)
            val retrofitData = retrofitBuilder.postSignUpData(personnelNo, password)
                .enqueue(object: Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        if (response.body()?.success == true){

                            Toast.makeText(context, response.body()?.message.toString(), Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_signupFragment_to_successFragment)
                        } else{
                            Toast.makeText(context, "User Already Exists", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        // TODO: Use the ViewModel
    }

}