package io.motoo.www.ui.mypage

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R

class MyPageAdapterViewHolder(itemView: View, private val itemClickListener: ItemClickListener) :
    RecyclerView.ViewHolder(itemView) {


    var mypage_recyclerView_item_button =
        itemView.findViewById<TextView>(R.id.mypage_recyclerView_item_button)
    var moneyType = itemView.findViewById<TextView>(R.id.moneyType)


    fun bindWithView() {

        var typeNum = 0
        if (adapterPosition == 1) {
            moneyType.text = "누적 상금액"
            mypage_recyclerView_item_button.text = "경기기록"
            typeNum = 1

        } else if (adapterPosition == 2) {
            moneyType.text = "상금 잔액"
            mypage_recyclerView_item_button.text = "출금하기"
            typeNum = 2
        } else {
            moneyType.text = "캐시 잔액"
            mypage_recyclerView_item_button.text = "출금하기"
            typeNum = 3
        }


        mypage_recyclerView_item_button.setOnClickListener {
            itemClickListener.onClickListener(
                adapterPosition,
                typeNum
            )
        }
    }


}
