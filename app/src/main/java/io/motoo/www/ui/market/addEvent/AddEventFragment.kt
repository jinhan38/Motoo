package io.motoo.www.ui.market.addEvent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import io.motoo.www.R
import io.motoo.www.databinding.FragmentAddEventBinding
import io.motoo.www.ui.game.gameDateMenu.GameInfoAdapter

class AddEventFragment : Fragment() {

    lateinit var b: FragmentAddEventBinding
    lateinit var addEventRecyclerViewAdapter: AddEventRecyclerViewAdapter
    var dataList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_add_event, container, false)

        recyclerViewSetting()


        return b.root
    }

    fun recyclerViewSetting() {

        dataList.clear()

        for (i in 0..30) {
            dataList.add("$i 번째 종목")
        }


        b.searchHistoryRecyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            addEventRecyclerViewAdapter = AddEventRecyclerViewAdapter()
            addEventRecyclerViewAdapter.addItem(dataList)
            adapter = addEventRecyclerViewAdapter
        }
    }

}