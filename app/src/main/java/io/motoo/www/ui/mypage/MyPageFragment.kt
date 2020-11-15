package io.motoo.www.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.motoo.www.R
import io.motoo.www.others.Utils
import io.motoo.www.others.replaceFragment
import io.motoo.www.ui.game.gameDateMenu.GameInfoAdapter
import io.motoo.www.ui.mypage.gameRecord.GameRecordFragment
import io.motoo.www.ui.signUp.SignUpProfile
import kotlinx.android.synthetic.main.fragment_mypage.view.*

class MyPageFragment : Fragment(), ItemClickListener {


    companion object {

        private const val 경기기록 = "경기기록"
        private const val 출금하기 = "출금하기"

        private const val TAG = "MyPageFragment"

        @Volatile
        private var instance: MyPageFragment? = null


        fun getInstance(): MyPageFragment =
            instance ?: synchronized(this) {
                instance ?: MyPageFragment().also {
                    instance = it
                }
            }
    }

    lateinit var v: View
    var dataList = ArrayList<String>()
    lateinit var myPageRecyclerViewAdapter: MyPageRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_mypage, container, false)
        recyclerViewSetting()
        return v
    }


    fun recyclerViewSetting() {

        dataList.clear()

        dataList.add("1")
        dataList.add("2")

        v.myPage_recyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            myPageRecyclerViewAdapter = MyPageRecyclerViewAdapter(this@MyPageFragment)
            myPageRecyclerViewAdapter.addItem(dataList)
            adapter = myPageRecyclerViewAdapter

        }
    }


    override fun onClickListener(position: Int, text: String) {
        Log.d(TAG, "onClickListener: 포지션 확인 $position")
        Log.d(TAG, "onClickListener: $text")
        if (text == 출금하기) {

        } else if (text == 경기기록) {
            GameRecordFragment().replaceFragment(R.id.fragment_container, requireActivity())

        }

    }

    override fun onResume() {
        super.onResume()
//        Utils.setStatusBarColor(requireActivity(), resources.getColor(R.color.primary_blue, null))
    }
}