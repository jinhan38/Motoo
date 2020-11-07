package io.motoo.www.ui.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.motoo.www.R
import io.motoo.www.ui.market.MarketFragment

class MyPageFragment : Fragment() {


    companion object {
        @Volatile
        private var instance: MyPageFragment? = null


        fun getInstance(): MyPageFragment =
            instance ?: synchronized(this) {
                instance ?: MyPageFragment().also {
                    instance = it
                }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

}