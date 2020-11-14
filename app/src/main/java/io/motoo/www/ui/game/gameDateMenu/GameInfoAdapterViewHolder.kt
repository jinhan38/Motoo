package io.motoo.www.ui.game.gameDateMenu

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.ui.game.`interface`.DetailInfoClickListener


class GameInfoAdapterViewHolder(itemView: View, private val context: Context, private val detailInfoClickListener: DetailInfoClickListener) :
    RecyclerView.ViewHolder(itemView){

    companion object {
        private const val TAG = "GameInfoAdapterViewHold"
    }


    fun bindWithView() {
        itemView.setOnClickListener {
            Log.d(TAG, "bindWithView: 포지션 $adapterPosition")
            detailInfoClickListener.onDetailInfoClick(adapterPosition)
        }


    }

}