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
import java.text.SimpleDateFormat

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

    var gameTimeList = ArrayList<String>()
    lateinit var gameDataModel: GameDataModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)


//        transaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, GameTodayMenuFragment.getInstance(), "game").commit()

        tabLayoutStickyToTop()
        topEventViewPager()
        recyclerViewSetting("없음", "1,000만원")
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

    private fun recyclerViewSetting(participationFee: String, playMoney: String) {

        gameTimeList.clear()
        gameTimeList.add("09:00 ~ 09:50")
        gameTimeList.add("10:00 ~ 10:50")
        gameTimeList.add("11:00 ~ 11:50")
        gameTimeList.add("12:00 ~ 12:50")
        gameTimeList.add("13:00 ~ 13:50")
        gameTimeList.add("14:00 ~ 14:50")
        gameTimeList.add("15:00 ~ 15:50")
        gameTimeList.add("16:00 ~ 16:50")
        gameTimeList.add("17:00 ~ 17:50")
        gameTimeList.add("18:00 ~ 18:50")
        gameTimeList.add("19:00 ~ 19:50")
        gameTimeList.add("20:00 ~ 20:50")
        gameTimeList.add("21:00 ~ 21:50")
        gameTimeList.add("22:00 ~ 22:50")
        
//        gameTimeList.add("2020-11-29 09:00 \n~ 2020-11-29 09:50")
//        gameTimeList.add("2020-11-29 10:00 \n~ 2020-11-29 10:50")
//        gameTimeList.add("2020-11-29 11:00 \n~ 2020-11-29 11:50")
//        gameTimeList.add("2020-11-29 12:00 \n~ 2020-11-29 12:50")
//        gameTimeList.add("2020-11-29 13:00 \n~ 2020-11-29 13:50")
//        gameTimeList.add("2020-11-29 14:00 \n~ 2020-11-29 14:50")
//        gameTimeList.add("2020-11-29 15:00 \n~ 2020-11-29 15:50")
//        gameTimeList.add("2020-11-29 16:00 \n~ 2020-11-29 16:50")
//        gameTimeList.add("2020-11-29 17:00 \n~ 2020-11-29 17:50")
//        gameTimeList.add("2020-11-29 18:00 \n~ 2020-11-29 18:50")
//        gameTimeList.add("2020-11-29 19:00 \n~ 2020-11-29 19:50")
//        gameTimeList.add("2020-11-29 20:00 \n~ 2020-11-29 20:50")
//        gameTimeList.add("2020-11-29 21:00 \n~ 2020-11-29 21:50")
//        gameTimeList.add("2020-11-29 22:00 \n~ 2020-11-29 22:50")
        gameTimeList.reverse()

//        gameDataList.add("4")
//        gameDataList.add("5")
//        gameDataList.add("6")

        //특정 날짜가 되면 해당날짜의 9~23시까지 13경기의 시작 시간대를 저장해라
        //보여줄 시간과, 밀리미세컨드 시간대 두가지가 필요하다.
        //변하는건 년월일, 안변하는건 시간대


        val time = System.currentTimeMillis() //현재시간 밀리세컨드로 불러옴
        val simpleDateHead = SimpleDateFormat("yyyy-MM-dd") //시작시간 계산 위한 dateFormat
        val headDate = simpleDateHead.format(time) // 현재시간을 년월일 string형식으로 변환
        val startTimeHour = ArrayList<Int>() //게임 시작 hour를 담은 list
        for (i in 9..22) {
            startTimeHour.add(i)
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS")
        val startTimeList = ArrayList<Long>() //게임 시작시간 list 9~22시까지 각 시간의 정각에 시작
        for (i in 0 until startTimeHour.size) {
            val strDate = "$headDate ${startTimeHour[i]}:00:00,000"
            val date = simpleDateFormat.parse(strDate)
            startTimeList.add(date!!.time)
        }

        var stopTimeIndex = 0
        for (i in 0 until startTimeList.size) {
            if (time < startTimeList[i]) {
                stopTimeIndex = i
                break
            }
        }
        
        if (stopTimeIndex == 0) stopTimeIndex = startTimeList.size
        
        for (i in 0 until stopTimeIndex) {
            gameTimeList.removeAt(0)
        }
        Log.d(TAG, "bindWithView: array : $gameTimeList")


        gameDataModel = GameDataModel(participationFee, playMoney, gameTimeList)
        Log.d(TAG, "recyclerViewSetting: gameDataModel : $gameDataModel")
        b.recyclerViewGameInfo.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            gameInfoAdapter = GameInfoAdapter(this@GameFragment)
            gameInfoAdapter.addItem(gameDataModel)
            adapter = gameInfoAdapter
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {

    }


    private fun gameDateMenuViewPager() {

        b.gameDateMenuTab.tabLayoutController { tab ->

//            transaction = childFragmentManager.beginTransaction()
            var participationFee: String = "";
            var playMoeny: String = "1,000만원"
            when (tab?.position) {
                0 -> {
                    participationFee = "없음"
                    Log.d(TAG, "setupLister: 연습경기")
                }
                1 -> {
                    participationFee = "1만원"
                    Log.d(TAG, "setupLister: 1만원")
                }
                2 -> {
                    participationFee = "10만원"
                    Log.d(TAG, "setupLister: expected")
                }
                3 -> {
                    participationFee = "50만원"
                    Log.d(TAG, "setupLister: ing")
                }
                4 -> {
                    participationFee = "100만원"
                    Log.d(TAG, "setupLister: ing")
                }
            }
            recyclerViewSetting(participationFee, playMoeny)

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