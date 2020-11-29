package io.motoo.www.ui.game.gameDateMenu

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import io.motoo.www.ui.game.`interface`.DetailInfoClickListener
import java.text.SimpleDateFormat


class GameInfoAdapterViewHolder(
    itemView: View,
    private val context: Context,
    private val detailInfoClickListener: DetailInfoClickListener
) :
    RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val TAG = "GameInfoAdapterViewHold"
    }

    private val gameTime = itemView.findViewById<TextView>(R.id.gameTime)
    private val participationFee = itemView.findViewById<TextView>(R.id.participationFee)
    private val playMoney = itemView.findViewById<TextView>(R.id.playMoney)

    @SuppressLint("SimpleDateFormat")
    fun bindWithView(gameDataModel: GameDataModel) {

        participationFee.text = gameDataModel.participationFee
        playMoney.text = gameDataModel.playMoney
        gameTime.text = gameDataModel.gameTime[adapterPosition]
        itemView.setOnClickListener {
            Log.d(TAG, "bindWithView: 포지션 $adapterPosition")
            detailInfoClickListener.onDetailInfoClick(adapterPosition)
        }

    }

}