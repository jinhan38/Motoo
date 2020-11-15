package io.motoo.www.ui.game

import android.annotation.SuppressLint
import android.content.Intent
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
import io.motoo.www.R
import io.motoo.www.databinding.FragmentGameBinding
import io.motoo.www.others.tabLayoutController
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.game.`interface`.DetailInfoClickListener
import io.motoo.www.ui.game.eventViewPager.FirstGameEventFragment
import io.motoo.www.ui.game.eventViewPager.SecondGameEventFragment
import io.motoo.www.ui.game.eventViewPager.ThirdGameEventFragment
import io.motoo.www.ui.game.eventViewPager.ViewPagerAdapter
import io.motoo.www.ui.game.gameDateMenu.*
import io.motoo.www.ui.login.LoginActivity

class GameFragment : Fragment(), View.OnClickListener, DetailInfoClickListener {

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

    var gameDataList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)


//        transaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, GameTodayMenuFragment.getInstance(), "game").commit()

        tabLayoutStickyToTop()
        topEventViewPager()
        recyclerViewSetting()
        gameDateMenuViewPager()

        return b.root


    }


    private fun tabLayoutStickyToTop() {
        b.scrollViewWrap.run {

            header = b.gameDateMenuTabWrap
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

        gameDataList.clear()
        gameDataList.add("1")
        gameDataList.add("2")
        gameDataList.add("3")
//        gameDataList.add("4")
//        gameDataList.add("5")
//        gameDataList.add("6")

        b.recyclerViewGameInfo.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            gameInfoAdapter = GameInfoAdapter(this@GameFragment)
            gameInfoAdapter.addItem(gameDataList)
            adapter = gameInfoAdapter

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    private fun gameDateMenuViewPager() {

        b.gameDateMenuTab.tabLayoutController { tab ->

//            transaction = childFragmentManager.beginTransaction()

            when (tab?.position) {
                0 -> {
                    Log.d(TAG, "setupLister: today")
//                    transaction.replace(R.id.fragment_container, GameTodayMenuFragment.getInstance())
                }
                1 -> {
                    Log.d(TAG, "setupLister: previous")
//                    transaction.replace(R.id.fragment_container, GamePreviousMenuFragment.getInstance())
                }
                2 -> {
                    Log.d(TAG, "setupLister: expected")
//                    transaction.replace(R.id.fragment_container, GameExpectedFragment.getInstance())
                }
                3 -> {
                    Log.d(TAG, "setupLister: ing")
//                    transaction.replace(R.id.fragment_container, GameIngMenuFragment.getInstance())
                }
            }

            //addToBackStack(null)을 추가하면 back 버튼이 먹음
//            transaction.addToBackStack(null)
//            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//            transaction.commit()
        }


        true

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
            val dpValue = 24
            val d: Float = resources.displayMetrics.density
            val margin = (dpValue * d).toInt()
            setPadding(margin, 0, margin, 0)
//            pageMargin = (24 * d).toInt()

        }
    }

    override fun onDetailInfoClick(position: Int) {
        Log.d(TAG, "onDetailInfoClick: 포지션 확인 : $position")

        //데이터 put해서 보낼 것
        startActivity(Intent(activity, GameRankingDetailInfoActivity::class.java))
        activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        Bottom.context.fragmentChange(RankingGameDetailInfoFragment.getInstance())

    }


}