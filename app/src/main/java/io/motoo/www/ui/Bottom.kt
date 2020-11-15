package io.motoo.www.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import io.motoo.www.R
import io.motoo.www.common.BaseActivity
import io.motoo.www.databinding.ActivityBottomBinding
import io.motoo.www.others.replaceFragment
import io.motoo.www.ui.game.GameFragment
import io.motoo.www.ui.game.eventViewPager.ViewPagerAdapter
import io.motoo.www.ui.market.MarketFragment
import io.motoo.www.ui.mypage.MyPageFragment
import io.motoo.www.ui.portfolio.PortfolioFragment


class Bottom : BaseActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "Bottom"
        lateinit var context: Bottom
    }

    lateinit var b: ActivityBottomBinding
    var gameFragment = GameFragment.getInstance()
    var marketFragment = MarketFragment.getInstance()
    var portfolioFragment = PortfolioFragment.getInstance()
    var myPageFragment = MyPageFragment.getInstance()
    lateinit var transaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        b = DataBindingUtil.setContentView(this, R.layout.activity_bottom)


        transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, GameFragment.getInstance(), "game").commit()

        val fm = supportFragmentManager

        val viewPagerAdapter = ViewPagerAdapter(fm = fm)
        viewPagerAdapter.addItem(gameFragment)
        viewPagerAdapter.addItem(marketFragment)
        viewPagerAdapter.addItem(portfolioFragment)
        viewPagerAdapter.addItem(myPageFragment)

//        b.bottomViewPager.offscreenPageLimit = 4
//        b.bottomViewPager.apply {
//            adapter = viewPagerAdapter
//        }
//        b.bottomViewPager.setPagingEnabled(false)
        setupLister()
    }


    override fun setupLister() {

        b.navView.setOnNavigationItemSelectedListener { item ->

            transaction = supportFragmentManager.beginTransaction()


            when (item.itemId) {
                R.id.navigation_game -> {
                    Log.d(TAG, "setupLister: 게임 클릭")
                    transaction.replace(R.id.fragment_container, GameFragment.getInstance(), "game")
                }
                R.id.navigation_market -> {
                    transaction.replace(
                        R.id.fragment_container,
                        MarketFragment.getInstance(),
                        "market"
                    )

                }
                R.id.navigation_portfolio -> {
                    transaction.replace(
                        R.id.fragment_container,
                        PortfolioFragment.getInstance(),
                        "portfolio"
                    )

                }
                R.id.navigation_mypage -> {
                    transaction.replace(
                        R.id.fragment_container,
                        MyPageFragment.getInstance(),
                        "mypage"
                    )
                }
            }


            //addToBackStack(null)을 추가하면 back 버튼이 먹음
//            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()

            true
        }
    }

    override fun onClick(p0: View?) {

    }

    

    fun fragmentChange(fragment: Fragment) {

        fragment.replaceFragment(R.id.fragment_container, this)
//        transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment, "game")
//        transaction.addToBackStack(null)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG, "onBackPressed: ")
    }
}