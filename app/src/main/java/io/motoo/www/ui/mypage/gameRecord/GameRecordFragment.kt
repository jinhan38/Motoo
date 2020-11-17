package io.motoo.www.ui.mypage.gameRecord

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.motoo.www.R
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import kotlinx.android.synthetic.main.fragment_game_record.view.*


class GameRecordFragment : Fragment() {

    companion object {

        private const val TAG = "GameRecordFragment"

        @Volatile
        private var instance: GameRecordFragment? = null

        fun getInstance(): GameRecordFragment =
            instance ?: synchronized(this) {
                instance ?: GameRecordFragment().also {
                    instance = it
                }
            }
    }

    lateinit var v: View
    lateinit var myGameRecordRecyclerViewAdapter: MyGameRecordRecyclerViewAdapter

    private var dataList = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        v = inflater.inflate(R.layout.fragment_game_record, container, false)

        recyclerViewSetting()

        v.back_button.setOnClickListener {
            Bottom.context.onBackPressed()
        }

        return v
    }

    private fun recyclerViewSetting() {
        dataList.clear()

        dataList.add("1")
        dataList.add("1")
        dataList.add("1")
        dataList.add("1")
        dataList.add("1")

        v.myRecord_recyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            myGameRecordRecyclerViewAdapter = MyGameRecordRecyclerViewAdapter()
            myGameRecordRecyclerViewAdapter.addItem(dataList)
            adapter = myGameRecordRecyclerViewAdapter

        }


    }


    override fun onStop() {
        super.onStop()
        Utils.setStatusBarColorBlue(requireActivity(), resources.getColor(R.color.primary_blue, null))
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}