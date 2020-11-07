package io.motoo.www.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.motoo.www.R
import io.motoo.www.common.BaseFragment
import io.motoo.www.ui.Bottom
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : BaseFragment(), View.OnClickListener {

    companion object {
        private const val TAG = "LoginFragment"
    }

    lateinit var v: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_login, container, false)
  
        setupListener()

        return v
    }

    override fun setupListener() {
        v.login_start_button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.login_start_button -> {
                startActivity(Intent(activity, Bottom::class.java))
            }

        }
    }


}