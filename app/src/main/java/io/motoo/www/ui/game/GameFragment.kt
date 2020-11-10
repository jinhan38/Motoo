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
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import io.motoo.www.R
//import io.motoo.www.databinding.FragmentGameBinding
import io.motoo.www.ui.game.eventViewPager.FirstGameEventFragment
import io.motoo.www.ui.game.eventViewPager.SecondGameEventFragment
import io.motoo.www.ui.game.eventViewPager.ThirdGameEventFragment
import io.motoo.www.ui.game.eventViewPager.ViewPagerAdapter
import io.motoo.www.ui.game.gameDateMenu.GameExpectedFragment
import io.motoo.www.ui.game.gameDateMenu.GameIngMenuFragment
import io.motoo.www.ui.game.gameDateMenu.GamePreviousMenuFragment
import io.motoo.www.ui.game.gameDateMenu.GameTodayMenuFragment

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
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var gameDateMenuViewPager : ViewPager
    lateinit var gameTopViewPager : ViewPager
    lateinit var gameDateMenuTab : TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_game, container, false)

        gameDateMenuViewPager = v.findViewById(R.id.gameDateMenuViewPager)
        gameTopViewPager = v.findViewById(R.id.game_top_viewPager)
        gameDateMenuTab = v.findViewById(R.id.gameDateMenuTab)

        topEventViewPager()
        gameDateMenuViewPager()

//        b.scrollViewWrap.run {
//
//            header = b.gameDateMenuTab
//            stickListener = { _ ->
//                Log.d(TAG, "onCreateView: 탭 바 상단에 붙음")
//            }
//            freeListener = { _ ->
//                Log.d(TAG, "onCreateView: 탭바 떨어져있음")
//            }
//        }

//        return b.root
        return v


    }

    fun setupListener() {

    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    private fun gameDateMenuViewPager() {
        var fm = childFragmentManager
        var viewPagerAdapter = ViewPagerAdapter(fm)
        viewPagerAdapter.addItem(GameTodayMenuFragment.getInstance())
        viewPagerAdapter.addItem(GamePreviousMenuFragment.getInstance())
        viewPagerAdapter.addItem(GameExpectedFragment.getInstance())
        viewPagerAdapter.addItem(GameIngMenuFragment.getInstance())
        gameDateMenuViewPager.apply {
            adapter = viewPagerAdapter
        }

    }

    private fun topEventViewPager() {

        fm = childFragmentManager

        viewPagerAdapter = ViewPagerAdapter(fm)
        viewPagerAdapter.addItem(FirstGameEventFragment.getInstance())
        viewPagerAdapter.addItem(SecondGameEventFragment.getInstance())
        viewPagerAdapter.addItem(ThirdGameEventFragment.getInstance())
        gameTopViewPager.apply {

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