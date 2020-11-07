package io.motoo.www.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import io.motoo.www.R
import io.motoo.www.common.BaseActivity
import io.motoo.www.databinding.ActivityBottomBinding
import io.motoo.www.ui.game.GameFragment
import io.motoo.www.ui.market.MarketFragment
import io.motoo.www.ui.mypage.MyPageFragment
import io.motoo.www.ui.portfolio.PortfolioFragment


class Bottom : BaseActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "Bottom"
    }

    lateinit var b: ActivityBottomBinding
    var gameFragment = GameFragment()
    var marketFragment = MarketFragment()
    var portfolioFragment = PortfolioFragment()
    var myPageFragment = MyPageFragment()
    lateinit var transaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_bottom)


        setupLister()
    }


    override fun setupLister() {

        b.navView.setOnNavigationItemSelectedListener { item ->

            transaction = supportFragmentManager.beginTransaction()

            when (item.itemId) {
                R.id.navigation_game -> {
                    Log.d(TAG, "setupLister: 게임 클릭")
                    transaction.replace(R.id.nav_host_fragment, GameFragment.getInstance(), "game")
                }
                R.id.navigation_market -> {
                    transaction.replace(
                        R.id.nav_host_fragment,
                        MarketFragment.getInstance(),
                        "market"
                    )

                }
                R.id.navigation_portfolio -> {
                    transaction.replace(
                        R.id.nav_host_fragment,
                        PortfolioFragment.getInstance(),
                        "portfolio"
                    )

                }
                R.id.navigation_mypage -> {
                    transaction.replace(
                        R.id.nav_host_fragment,
                        MyPageFragment.getInstance(),
                        "mypage"
                    )
                }
            }

            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()

            true
        }
    }

    override fun onClick(p0: View?) {

    }

}