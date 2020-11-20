package io.motoo.www.ui.mypage.setting

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentSettingBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom

class SettingFragment : Fragment(), View.OnClickListener {

    lateinit var b: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        container?.removeAllViews()
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        setupListener()

        // Inflate the layout for this fragment
        return b.root
    }

    private fun setupListener(){
        b.profileEditButton.setOnClickListener(this)
        b.backButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.profile_edit_button -> {
                Bottom.context.fragmentChange(ProfileEditFragment())
            }
            R.id.alarm_edit_button -> {

            }
            R.id.frequent_question -> {

            }
            R.id.notice_board -> {

            }
            R.id.privacy_question -> {

            }
            R.id.improveAndSuggestion -> {

            }
            R.id.inviteAndGetCash -> {

            }
            R.id.back_button ->{
                Bottom.context.onBackPressed()
            }

        }
    }

    override fun onStop() {
        super.onStop()
        Utils.setStatusBarColorBlue(
            requireActivity(),
            resources.getColor(R.color.primary_blue, null)
        )
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}