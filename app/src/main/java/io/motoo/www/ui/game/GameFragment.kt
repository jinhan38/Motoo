package io.motoo.www.ui.game

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import io.motoo.www.R
import io.motoo.www.databinding.FragmentGameBinding
import io.motoo.www.others.tabLayoutController
import io.motoo.www.ui.Bottom
//import io.motoo.www.databinding.FragmentGameBinding
import io.motoo.www.ui.game.eventViewPager.FirstGameEventFragment
import io.motoo.www.ui.game.eventViewPager.SecondGameEventFragment
import io.motoo.www.ui.game.eventViewPager.ThirdGameEventFragment
import io.motoo.www.ui.game.eventViewPager.ViewPagerAdapter
import io.motoo.www.ui.game.gameDateMenu.*
import io.motoo.www.ui.market.MarketFragment
import io.motoo.www.ui.mypage.MyPageFragment
import io.motoo.www.ui.portfolio.PortfolioFragment

class GameFragment : Fragment(), View.OnClickListener {

    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: GameFragment? = null

        fun getInstance(): GameFragment =
            instance ?: synchronized(this) {
                instance ?: GameFragment().also {
                    instance = it
                }
            }
    }

    lateinit var fm: FragmentManager
    lateinit var transaction: FragmentTransaction
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var b: FragmentGameBinding
    lateinit var gameInfoAdapter: GameInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)


//        transaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, GameTodayMenuFragment.getInstance(), "game").commit()

        tabLayoutStickyToTop()
        topEventViewPager()
//        gameDateMenuViewPager()
        recyclerViewSetting()


        return b.root


    }


    private fun tabLayoutStickyToTop() {
        b.scrollViewWrap.run {

            header = b.gameDateMenuTab
            stickListener = { _ ->
                Log.d(TAG, "onCreateView: 탭 바 상단에 붙음")
            }
            freeListener = { _ ->
                Log.d(TAG, "onCreateView: 탭바 떨어져있음")
            }
        }
    }

    fun setupListener() {


    }

    fun recyclerViewSetting() {

        var stringList = ArrayList<String>()
        stringList.add("1")
        stringList.add("2")
        stringList.add("3")
        stringList.add("4")
        stringList.add("5")
        stringList.add("6")

        b.recyclerViewGameInfo.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            gameInfoAdapter = GameInfoAdapter()
            gameInfoAdapter.addItem(stringList)
            adapter = gameInfoAdapter

        }
    }

    fun recyclerViewAddItem() {

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    private fun gameDateMenuViewPager() {
//        var fm = childFragmentManager
//        var viewPagerAdapter = ViewPagerAdapter(fm)
//        viewPagerAdapter.addItem(GameTodayMenuFragment.getInstance())
//        viewPagerAdapter.addItem(GamePreviousMenuFragment.getInstance())
//        viewPagerAdapter.addItem(GameExpectedFragment.getInstance())
//        viewPagerAdapter.addItem(GameIngMenuFragment.getInstance())
//        b.gameDateMenuViewPager.apply {
//            adapter = viewPagerAdapter
//        }


//        b.gameDateMenuTab.tabLayoutController {tab ->
//
//
//            transaction = childFragmentManager.beginTransaction()
//
//            when(tab?.position)     {
//                0 -> {
//                    Log.d(TAG, "setupLister: 게임 클릭")
//                    transaction.replace(R.id.fragment_container, GameTodayMenuFragment.getInstance())
//                }
//                1 -> {
//                    Log.d(TAG, "setupLister: 게임 클릭")
//                    transaction.replace(R.id.fragment_container, GamePreviousMenuFragment.getInstance())
//                }
//                2 -> {
//                    Log.d(TAG, "setupLister: 게임 클릭")
//                    transaction.replace(R.id.fragment_container, GameExpectedFragment.getInstance())
//                }
//                3 -> {
//                    Log.d(TAG, "setupLister: 게임 클릭")
//                    transaction.replace(R.id.fragment_container, GameIngMenuFragment.getInstance())
//                }
//            }
//
//            //addToBackStack(null)을 추가하면 back 버튼이 먹음
////            transaction.addToBackStack(null)
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//            transaction.commit()
//        }


//        true

    }

    private fun topEventViewPager() {

        fm = childFragmentManager

        viewPagerAdapter = ViewPagerAdapter(fm)
        viewPagerAdapter.addItem(FirstGameEventFragment.getInstance())
        viewPagerAdapter.addItem(SecondGameEventFragment.getInstance())
        viewPagerAdapter.addItem(ThirdGameEventFragment.getInstance())
        b.gameTopViewPager.apply {

            Log.d(TAG, "onStart: ")
            setupListener()
            adapter = viewPagerAdapter

            //viewpager 좌우 간격 만드는 부분
            clipToPadding = false
            val dpValue = 32
            val d: Float = resources.displayMetrics.density
            val margin = (dpValue * d).toInt()
            setPadding(margin, 0, margin, 0)
            pageMargin = (24 * d).toInt()

        }
    }


}