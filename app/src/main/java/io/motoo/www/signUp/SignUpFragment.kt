package io.motoo.www.signUp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.motoo.www.R
import io.motoo.www.customInterface.BackButton
import io.motoo.www.login.LoginFragment
import io.motoo.www.others.KeyboardVisibilityUtils
import io.motoo.www.others.replaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


class SignUpFragment : Fragment() {
    companion object {
        private const val TAG = "SignUpFragment"
    }

    lateinit var v: View
    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_sign_up, container, false)

        activity?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        v.next.setOnClickListener {
            Verification().replaceFragment(R.id.fragment, requireActivity())
        }



        v.back_button.setOnClickListener {
            Log.d(TAG, "onCreateView: 클릭")
            activity?.onBackPressed()
        }

        return v
    }


}