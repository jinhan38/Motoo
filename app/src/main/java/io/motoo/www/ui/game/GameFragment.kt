package io.motoo.www.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import io.motoo.www.R
import io.motoo.www.databinding.FragmentGameBinding
import io.motoo.www.ui.game.viewPager.FirstFragment
import io.motoo.www.ui.game.viewPager.SecondFragment
import io.motoo.www.ui.game.viewPager.ThirdFragment
import io.motoo.www.ui.game.viewPager.ViewPagerAdapter

class GameFragment : Fragment() {

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

    lateinit var b: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        val fm = fragmentManager
        val viewPagerAdapter = ViewPagerAdapter(fm!!)
        viewPagerAdapter.addItem(FirstFragment())
        viewPagerAdapter.addItem(SecondFragment())
        viewPagerAdapter.addItem(ThirdFragment())

        b.gameTopViewPager.apply {

            adapter = viewPagerAdapter

            //viewpager 좌우 간격 만드는 부분
            clipToPadding = false
            val dpValue = 32
            val d: Float = resources.displayMetrics.density
            val margin = (dpValue * d).toInt()
            setPadding(margin, 0, margin, 0)
            pageMargin = (24 * d).toInt()

            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(b.gameTopTabLayout))

//            b.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
//                override fun onTabSelected(tab: TabLayout.Tab) {
//                    b.followsViewPager.setCurrentItem(tab.position)
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab) {}
//                override fun onTabReselected(tab: TabLayout.Tab) {}
//            })
            b.gameTopTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    b.gameTopViewPager.currentItem = tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }



        return b.root


    }

}