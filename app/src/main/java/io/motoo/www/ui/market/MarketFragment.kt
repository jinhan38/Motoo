package io.motoo.www.ui.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentMarketBinding
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.game.GameFragment
import io.motoo.www.ui.market.addEvent.AddEventFragment

class MarketFragment : Fragment() {

    companion object {
        @Volatile
        private var instance: MarketFragment? = null


        fun getInstance(): MarketFragment =
            instance ?: synchronized(this) {
                instance ?: MarketFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b : FragmentMarketBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_market, container, false)

        
        b.moveToAddEvent.setOnClickListener {
            Bottom.context.fragmentChange(AddEventFragment())
        }
        return b.root
    }

}