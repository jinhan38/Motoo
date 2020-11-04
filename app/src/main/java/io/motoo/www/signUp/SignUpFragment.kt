package io.motoo.www.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.motoo.www.R
import io.motoo.www.login.LoginFragment
import io.motoo.www.others.KeyboardVisibilityUtils
import io.motoo.www.others.replaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment() {

    private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        move_to_email_login_button.setOnClickListener {
            LoginFragment().replaceFragment(R.id.fragment, requireActivity())
        }
    }
}