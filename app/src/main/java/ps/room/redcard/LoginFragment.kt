package ps.room.redcard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import ps.room.redcard.api.LoginApi
import ps.room.redcard.data.User
import ps.room.redcard.data.UserData
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginAuth = view.findViewById<View>(R.id.log_in_auth)
        loginAuth.setOnClickListener {
            val personnelNoTv = view.findViewById<View>(R.id.LoginPersonnelEdit) as TextInputEditText
            val personnelNo = personnelNoTv.text.toString()
            val passwordTv = view.findViewById<View>(R.id.LoginPasswordEdit) as TextInputEditText
            val password = passwordTv.text.toString()

            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://RedCard.onotuumoru.repl.co/")
                .build()
                .create(LoginApi::class.java)


            val retrofitData = retrofitBuilder.postData(personnelNo, password)
                .enqueue(object: Callback<UserData>{
                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        if(response.body()?.user == null){
                            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
                        } else{
                            val mBundle = Bundle()
                            mBundle.putString("personnelNo", response.body()?.user?.personnelNo)
                            Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show()
                            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment, mBundle)
                        }
                    }

                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })


        }
    }

}