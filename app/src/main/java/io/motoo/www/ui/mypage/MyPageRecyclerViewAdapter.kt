package io.motoo.www.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R


class MyPageRecyclerViewAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MyPageAdapterViewHolder>() {

    private var dataList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageAdapterViewHolder {
        return MyPageAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.mypage_recyclerview_item, parent, false),
            itemClickListener
        )

    }

    override fun onBindViewHolder(holder: MyPageAdapterViewHolder, position: Int) {
        holder.bindWithView()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }

}