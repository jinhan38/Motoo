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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import info.jeovani.viewpagerindicator.ViewPagerIndicator
import io.motoo.www.R
import io.motoo.www.databinding.FragmentGameBinding
import io.motoo.www.others.replaceFragment
import io.motoo.www.signUp.SignUpActivity
import io.motoo.www.ui.game.viewPager.FirstGameEventFragment
import io.motoo.www.ui.game.viewPager.SecondGameEventFragment
import io.motoo.www.ui.game.viewPager.ThirdGameEventFragment
import io.motoo.www.ui.game.viewPager.GameEventViewPagerAdapter

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

    lateinit var b: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        Log.d(TAG, "onCreateView: ")
        val fm = fragmentManager
        val viewPagerAdapter = GameEventViewPagerAdapter(fm!!)
        viewPagerAdapter.addItem(FirstGameEventFragment.getInstance())
        viewPagerAdapter.addItem(SecondGameEventFragment.getInstance())
        viewPagerAdapter.addItem(ThirdGameEventFragment.getInstance())

        b.gameTopViewPager.apply {

            setupListener()
            adapter = viewPagerAdapter

            //viewpager 좌우 간격 만드는 부분
            clipToPadding = false
            val dpValue = 32
            val d: Float = resources.displayMetrics.density
            val margin = (dpValue * d).toInt()
            setPadding(margin, 0, margin, 0)
            pageMargin = (24 * d).toInt()

//            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(b.gameTopTabLayout))

//            b.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
//                override fun onTabSelected(tab: TabLayout.Tab) {
//                    b.followsViewPager.setCurrentItem(tab.position)
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab) {}
//                override fun onTabReselected(tab: TabLayout.Tab) {}
//            })

//            b.gameTopTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
//                override fun onTabSelected(tab: TabLayout.Tab?) {
//                    b.gameTopViewPager.currentItem = tab!!.position
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab?) {
//                }
//
//                override fun onTabReselected(tab: TabLayout.Tab?) {
//                }
//
//            })
        }


        b.scrollViewWrap.run {

            header = b.gameMenuTab
            stickListener = { _ ->
                Log.d(TAG, "onCreateView: 탭 바 상단에 붙음")
            }
            freeListener = { _ ->
                Log.d(TAG, "onCreateView: 탭바 떨어져있음")
            }
        }

        return b.root


    }

    fun setupListener() {
        b.gameListAllButton.setOnClickListener(this)
        b.gameListRankingButton.setOnClickListener(this)
        b.gameListBenefitButton.setOnClickListener(this)
        b.goDetailRankingInfo.setOnClickListener(this)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.goDetailRankingInfo -> {
                activity?.startActivity(Intent(activity, GameRankingDetailInfo::class.java))
                activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            R.id.game_list_all_button -> {

                b.gameListAllButton.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                b.gameListAllButton.setTextColor(Color.WHITE)
                b.gameListRankingButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListRankingButton.setTextColor(resources.getColor(R.color.font_light, null))
                b.gameListBenefitButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListBenefitButton.setTextColor(resources.getColor(R.color.font_light, null))
            }

            R.id.game_list_ranking_button -> {

                b.gameListRankingButton.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                b.gameListRankingButton.setTextColor(Color.WHITE)
                b.gameListAllButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListAllButton.setTextColor(resources.getColor(R.color.font_light, null))
                b.gameListBenefitButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListBenefitButton.setTextColor(resources.getColor(R.color.font_light, null))

            }

            R.id.game_list_benefit_button -> {

                b.gameListBenefitButton.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                b.gameListBenefitButton.setTextColor(Color.WHITE)
                b.gameListAllButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListAllButton.setTextColor(resources.getColor(R.color.font_light, null))
                b.gameListRankingButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListRankingButton.setTextColor(resources.getColor(R.color.font_light, null))

            }
        }
    }


}