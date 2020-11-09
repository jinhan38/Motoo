package io.motoo.www.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import io.motoo.www.R
import io.motoo.www.common.BaseActivity
import io.motoo.www.databinding.ActivityBottomBinding
import io.motoo.www.ui.game.GameFragment
import io.motoo.www.ui.game.viewPager.FirstGameEventFragment
import io.motoo.www.ui.game.viewPager.GameEventViewPagerAdapter
import io.motoo.www.ui.game.viewPager.SecondGameEventFragment
import io.motoo.www.ui.game.viewPager.ThirdGameEventFragment
import io.motoo.www.ui.market.MarketFragment
import io.motoo.www.ui.mypage.MyPageFragment
import io.motoo.www.ui.portfolio.PortfolioFragment


class Bottom : BaseActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "Bottom"
    }

    lateinit var b: ActivityBottomBinding
    var gameFragment = GameFragment.getInstance()
    var marketFragment = MarketFragment.getInstance()
    var portfolioFragment = PortfolioFragment.getInstance()
    var myPageFragment = MyPageFragment.getInstance()
    lateinit var transaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_bottom)
        val fm = supportFragmentManager

        val viewPagerAdapter = GameEventViewPagerAdapter(fm = fm)
        viewPagerAdapter.addItem(gameFragment)
        viewPagerAdapter.addItem(marketFragment)
        viewPagerAdapter.addItem(portfolioFragment)
        viewPagerAdapter.addItem(myPageFragment)

        b.bottomViewPager.offscreenPageLimit = 4
        b.bottomViewPager.apply {
            adapter = viewPagerAdapter
        }
//        b.bottomViewPager.setPagingEnabled(false)
        setupLister()
    }


    override fun setupLister() {

        b.navView.setOnNavigationItemSelectedListener { item ->

//            transaction = supportFragmentManager.beginTransaction()

            when (item.itemId) {
                R.id.navigation_game -> {
                    b.bottomViewPager.currentItem = 0
//                    Log.d(TAG, "setupLister: 게임 클릭")
//                    transaction.replace(R.id.nav_host_fragment, gameFragment, "game")
                }
                R.id.navigation_market -> {
                    b.bottomViewPager.currentItem = 1
//                    transaction.replace(
//                        R.id.nav_host_fragment,
//                        marketFragment,
//                        "market"
//                    )

                }
                R.id.navigation_portfolio -> {
                    b.bottomViewPager.currentItem = 2
//                    transaction.replace(
//                        R.id.nav_host_fragment,
//                        portfolioFragment,
//                        "portfolio"
//                    )

                }
                R.id.navigation_mypage -> {
                    b.bottomViewPager.currentItem = 3
//                    transaction.replace(
//                        R.id.nav_host_fragment,
//                        myPageFragment,
//                        "mypage"
//                    )
                }
            }

            //addToBackStack(null)을 추가하면 back 버튼이 먹음
//            transaction.addToBackStack(null)
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//            transaction.commit()

            true
        }
    }

    override fun onClick(p0: View?) {

    }

}