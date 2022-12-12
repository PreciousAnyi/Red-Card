package ps.room.redcard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    private lateinit var viewModel: SignupViewModel
    private lateinit var button: Button
    private lateinit var personnelNoEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var checkbox: CheckBox
    private lateinit var signIn: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        personnelNoEditText = view.findViewById(R.id.SignupPersonnelEdit)
        signIn = view.findViewById(R.id.signupSignin)
        passwordEditText = view.findViewById(R.id.SignupPasswordEdit)
        confirmPasswordEditText = view.findViewById(R.id.SignupConfirmPasswordEdit)
        button = view.findViewById(R.id.log_in_auth)
        /*button.alpha=.25f
        button.isEnabled=false*/
        checkbox = view.findViewById(R.id.checkBox)
/*

        val validPassword = validPassword()
        val  validConfirmPassword = validConfirmPassword()

        if(checkbox.equals("true") && validPassword== null && validConfirmPassword== null){
            button.alpha=1f
            button.isEnabled=true
        }
        else{
            button.isEnabled=false
        }
*/



        button.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_successFragment)

        }

        signIn.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun validPassword(): String? {
        val password = passwordEditText.text.toString()
        if (password.length<8){
            return "Minimum 8 Character Password"
        }
        if (!password.matches(".*[A-Z].*".toRegex())){
            return "Your password must include atleast one capital letter"
        }
        if (!password.matches(".*[a-z].*".toRegex())){
            return "Your password must include atleast one small letter"
        }
        if (!password.matches(".*[$#@^&()_!,;:=+.].*".toRegex())){
            return "Your password must include atleast one special character"
        }
        else{
            return null

        }
    }

    private fun validConfirmPassword(): String? {
        val cpassword=confirmPasswordEditText.text.toString()
        if (cpassword!=passwordEditText.text.toString()){
            return "Your password does not match"
        }
        else {
            return null
        }
    }

    private fun validateButton() {
        val validPassword = validPassword()==null
        val validConfirmPassword = validConfirmPassword()==null
        if(checkbox.equals("true") && validPassword== null && validConfirmPassword== null){
            button.alpha=1f
            button.isEnabled=true
        }
        else{
            button.isEnabled=false
        }
    }

}