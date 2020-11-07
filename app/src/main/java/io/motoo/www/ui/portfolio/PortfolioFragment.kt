package io.motoo.www.ui.portfolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.motoo.www.R
import io.motoo.www.ui.mypage.MyPageFragment

class PortfolioFragment : Fragment() {


    companion object {
        @Volatile
        private var instance: PortfolioFragment? = null


        fun getInstance(): PortfolioFragment =
            instance ?: synchronized(this) {
                instance ?: PortfolioFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

}