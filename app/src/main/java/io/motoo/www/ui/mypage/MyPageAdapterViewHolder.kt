package io.motoo.www.ui.mypage

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R

class MyPageAdapterViewHolder(itemView: View, private val itemClickListener: ItemClickListener) :
    RecyclerView.ViewHolder(itemView) {


    var button = itemView.findViewById<TextView>(R.id.mypage_recyclerView_item_button)


    fun bindWithView() {

        if (adapterPosition ==1){
            button.text = "경기기록"

        }else{
            button.text = "출금하기"
        }


        button.setOnClickListener {
            itemClickListener.onClickListener(adapterPosition, button.text.toString())
        }
    }


}
