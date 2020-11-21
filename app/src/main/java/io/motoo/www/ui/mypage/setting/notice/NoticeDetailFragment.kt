package io.motoo.www.ui.mypage.setting.notice

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentNoticeDetailBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.mypage.setting.oneoncContact.AddOneOneContactFragment

class NoticeDetailFragment : Fragment() {

    companion object {

        @Volatile
        private var instance: NoticeDetailFragment? = null

        fun getInstance(): NoticeDetailFragment =
            instance ?: synchronized(this) {
                instance ?: NoticeDetailFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentNoticeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_detail, container, false)

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