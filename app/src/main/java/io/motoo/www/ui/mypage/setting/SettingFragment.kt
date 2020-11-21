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
import io.motoo.www.ui.mypage.setting.alarm.AlarmFragment
import io.motoo.www.ui.mypage.setting.faq.FAQFragment
import io.motoo.www.ui.mypage.setting.notice.NoticeFragment
import io.motoo.www.ui.mypage.setting.oneoncContact.OneOneContactFragment
import io.motoo.www.ui.mypage.setting.profile.ProfileEditFragment

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

    private fun setupListener() {
        b.backButton.setOnClickListener(this)
        b.profileEditButton.setOnClickListener(this)
        b.frequentQuestion.setOnClickListener(this)
        b.noticeBoard.setOnClickListener(this)
        b.alarmEditButton.setOnClickListener(this)
        b.oneOneContact.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.profile_edit_button -> {
                Bottom.context.fragmentChange(ProfileEditFragment.getInstance())
            }
            R.id.alarm_edit_button -> {
                Bottom.context.fragmentChange(AlarmFragment.getInstance())
            }
            R.id.frequent_question -> {
                Bottom.context.fragmentChange(FAQFragment.getInstance())
            }
            R.id.notice_board -> {
                Bottom.context.fragmentChange(NoticeFragment.getInstance())
            }
            R.id.one_one_contact -> {
                Bottom.context.fragmentChange(OneOneContactFragment.getInstance())
            }
            R.id.improveAndSuggestion -> {

            }
            R.id.inviteAndGetCash -> {

            }
            R.id.back_button -> {
                Bottom.context.onBackPressed()
            }

        }
    }

    override fun onStop() {
        super.onStop()
    }


    override fun onPause() {
        super.onPause()
        Utils.setStatusBarColorBlue(
            requireActivity(), resources.getColor(R.color.primary_blue, null)
        )
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }
}