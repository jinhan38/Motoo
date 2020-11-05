package io.motoo.www.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import io.motoo.www.R
import io.motoo.www.customInterface.BackButton
import io.motoo.www.others.replaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.back_button
import kotlinx.android.synthetic.main.fragment_sign_up.view.next
import kotlinx.android.synthetic.main.fragment_terms.view.*
import kotlinx.android.synthetic.main.fragment_verification.view.*

class Verification : Fragment() {
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_verification, container, false)

        activity?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        v.next.setOnClickListener {
            SignUpFinish().replaceFragment(R.id.fragment, requireActivity())
        }

        v.back_button.setOnClickListener {
            activity?.onBackPressed()
        }

        return v
    }


}