package io.motoo.www.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.motoo.www.R
import io.motoo.www.others.replaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up_verification.view.*


class SignUpVerification : Fragment() {
    lateinit var v: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sign_up_verification, container, false)

        activity?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        v.next.setOnClickListener {
            findNavController().navigate(R.id.action_verification_to_signUpFinish)
//            SignUpNickName().replaceFragment(R.id.fragment, requireActivity())
        }

        v.back_button.setOnClickListener {
            findNavController().popBackStack()
        }

        
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.country_num,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            v.spinner.adapter = adapter
        }


        return v
    }


}