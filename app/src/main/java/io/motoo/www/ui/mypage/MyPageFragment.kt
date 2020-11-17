package io.motoo.www.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import io.motoo.www.R
import io.motoo.www.others.replaceFragment
import io.motoo.www.ui.mypage.MyPageWithdrawMoney.MyPageWithdrawMoneyFragment
import io.motoo.www.ui.mypage.gameRecord.GameRecordFragment
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
        dataList.add("3")

        v.myPage_recyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            myPageRecyclerViewAdapter = MyPageRecyclerViewAdapter(this@MyPageFragment)
            myPageRecyclerViewAdapter.addItem(dataList)
            adapter = myPageRecyclerViewAdapter

        }
    }


    override fun onClickListener(position: Int, typeNum: Int) {

        when (typeNum) {
            1 -> {
                GameRecordFragment().replaceFragment(R.id.fragment_container, requireActivity())
            }
            2 -> {

                parentFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace(
                        R.id.fragment_container,
                        MyPageWithdrawMoneyFragment.newInstance(2)
                    )
                    addToBackStack(null)
                }


            }

            3 -> {

                parentFragmentManager.commit {

                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace(
                        R.id.fragment_container,
                        MyPageWithdrawMoneyFragment.newInstance(3)
                    )
                    addToBackStack(null)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
//        Utils.setStatusBarColor(requireActivity(), resources.getColor(R.color.primary_blue, null))
    }
}
