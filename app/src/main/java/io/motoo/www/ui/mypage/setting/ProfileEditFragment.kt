package io.motoo.www.ui.mypage.setting

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentProfileEditBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom

class ProfileEditFragment : Fragment(), View.OnClickListener {


    lateinit var b: FragmentProfileEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_edit, container, false)

        setupListener()

        return b.root

    }

    private fun setupListener(){
        b.backButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.back_button -> {
                Bottom.context.onBackPressed()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}