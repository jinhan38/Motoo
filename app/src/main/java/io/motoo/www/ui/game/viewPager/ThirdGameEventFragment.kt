package io.motoo.www.ui.game.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.motoo.www.R

class ThirdGameEventFragment : Fragment() {


    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: ThirdGameEventFragment? = null

        fun getInstance(): ThirdGameEventFragment =
            instance ?: synchronized(this) {
                instance ?: ThirdGameEventFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

}