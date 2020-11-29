package io.motoo.www.ui.market

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentMarketNoEventDataBinding


class MarketNoEventDataFragment : Fragment() {

    companion object {
        @Volatile
        private var instance: MarketNoEventDataFragment? = null


        fun getInstance(): MarketNoEventDataFragment =
            instance ?: synchronized(this) {
                instance ?: MarketNoEventDataFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentMarketNoEventDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_market_no_event_data,
            container,
            false
        )

        return b.root
    }

}