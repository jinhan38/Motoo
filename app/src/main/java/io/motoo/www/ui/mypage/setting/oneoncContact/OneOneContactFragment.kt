package io.motoo.www.ui.mypage.setting.oneoncContact

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentOneOneContactBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.game.GameFragment


class OneOneContactFragment : Fragment() {

    lateinit var b: FragmentOneOneContactBinding

    companion object {


        @Volatile
        private var instance: OneOneContactFragment? = null

        fun getInstance(): OneOneContactFragment =
            instance ?: synchronized(this) {
                instance ?: OneOneContactFragment().also {
                    instance = it
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_one_one_contact, container, false)

        b.backButton.setOnClickListener {
            Bottom.context.onBackPressed()
        }

        b.oneOneAdd.setOnClickListener {
            Bottom.context.fragmentChange(AddOneOneContactFragment.getInstance())
        }
        return b.root
    }


    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }
}