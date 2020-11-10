package io.motoo.www.ui.game.eventViewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.motoo.www.R

class FirstGameEventFragment : Fragment() {


    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: FirstGameEventFragment? = null

        fun getInstance(): FirstGameEventFragment =
            instance ?: synchronized(this) {
                instance ?: FirstGameEventFragment().also {
                    instance = it
                }
            }
    }

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