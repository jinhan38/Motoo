package io.motoo.www.ui.mypage.setting.alarm

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentAlarmBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.mypage.setting.faq.FAQFragment

class AlarmFragment : Fragment() {


    companion object {

        @Volatile
        private var instance: AlarmFragment? = null

        fun getInstance(): AlarmFragment =
            instance ?: synchronized(this) {
                instance ?: AlarmFragment().also {
                    instance = it
                }
            }
    }


    lateinit var b: FragmentAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false)

        b.backButton.setOnClickListener {
            Bottom.context.onBackPressed()
        }

        return b.root
    }


    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}