package io.motoo.www.ui.game.gameDateMenu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import io.motoo.www.ui.game.`interface`.DetailInfoClickListener
import kotlinx.android.synthetic.main.fragment_game_today_menu.view.game_list_all_button
import kotlinx.android.synthetic.main.game_info_recyclerview_header.view.*

class GameInfoAdapter(private val detailInfoClickListener: DetailInfoClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var TYPE_HEADER = 0
        var TYPE_ITEM = 1
        private const val TAG = "GameInfoAdapter"
    }


    lateinit var list: List<String>

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }

    }

    // onCreateViewHolder에서 inflate하는 부분을 간단하게 하기 위해 추가
    private fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Log.d(TAG, "onCreateViewHolder: viewType : $viewType")

        return if (viewType == TYPE_HEADER) {


            GameInfoHeaderViewHolder(parent.inflate(R.layout.game_info_recyclerview_header))

        } else {

            GameInfoAdapterViewHolder(
                parent.inflate(R.layout.game_info_recyclerview_item),
                parent.context, detailInfoClickListener
            )

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is GameInfoHeaderViewHolder -> {
                val headerHolder: GameInfoHeaderViewHolder = holder
                Log.d(TAG, "onBindViewHolder: 버튼 텍스트 ${headerHolder.itemView.game_list_all_button}")

                headerHolder.itemView.header_wrap.visibility = View.VISIBLE
                headerHolder.setupListener()
            }

            else -> {
                val itemHolder = holder as GameInfoAdapterViewHolder
                itemHolder.bindWithView()
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    fun addItem(list: List<String>) {
        this.list = list

    }


}
