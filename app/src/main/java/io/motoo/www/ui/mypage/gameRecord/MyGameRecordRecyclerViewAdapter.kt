package io.motoo.www.ui.mypage.gameRecord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R


class MyGameRecordRecyclerViewAdapter() : RecyclerView.Adapter<MyGameRecordViewHolder>() {


    private var dataList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGameRecordViewHolder {
        return MyGameRecordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.myrecord_recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyGameRecordViewHolder, position: Int) {
        holder.bindWithView()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }
}