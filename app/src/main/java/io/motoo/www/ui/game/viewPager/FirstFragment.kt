package io.motoo.www.ui.game.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.motoo.www.R

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        if (arguments != null) {
//            val args = arguments
//            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
//            view.setImageResource(args!!.getInt("imgRes"))
//        }
        
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

}