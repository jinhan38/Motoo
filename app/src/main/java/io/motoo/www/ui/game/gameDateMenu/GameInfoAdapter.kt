package io.motoo.www.ui.game.gameDateMenu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import kotlinx.android.synthetic.main.fragment_game_today_menu.view.game_list_all_button
import kotlinx.android.synthetic.main.game_info_recyclerview_header.view.*

class GameInfoAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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


            HeaderViewHolder(parent.inflate(R.layout.game_info_recyclerview_header))

        } else {

            GameInfoAdapterViewHolder(
                parent.inflate(R.layout.game_info_recyclerview_item),
                parent.context
            )

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HeaderViewHolder -> {
                val headerHolder: HeaderViewHolder = holder
                Log.d(TAG, "onBindViewHolder: 버튼 텍스트 ${headerHolder.itemView.game_list_all_button}")

                headerHolder.itemView.header_wrap.visibility = View.VISIBLE

            }
            else -> {
                val itemHolder = holder as GameInfoAdapterViewHolder
                itemHolder.bindWithView()
            }
        }

//        if (holder is HeaderViewHolder) {
//            var headerHolder = holder
//
//
//        } else {
//
//            var itemHolder = holder as GameInfoAdapterViewHolder
//            itemHolder.bindWithView()
//        }

    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    fun addItem(list: List<String>) {
        this.list = list

    }


}

class HeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView),
    View.OnClickListener {

    val all = headerView.game_list_all_button
    val ranking = headerView.game_list_ranking_button
    val benefit = headerView.game_list_ranking_button
    val context = headerView.context

    @SuppressLint("UseCompatLoadingForDrawables")
    
    override fun onClick(p0: View?) {


        Log.d("TAG", "onClick: 클릭")
        
        when(p0?.id){

        R.id.game_list_all_button -> {

            all.setBackgroundColor(
                context.resources.getColor(
                    R.color.primary_blue,
                    null
                )
            )
            all.setTextColor(Color.WHITE)
            ranking.background =
                context.resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
            ranking.setTextColor(context.resources.getColor(R.color.font_light, null))
            benefit.background =
                context.resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
            benefit.setTextColor(context.resources.getColor(R.color.font_light, null))
        }

        R.id.game_list_ranking_button -> {

            ranking.setBackgroundColor(
                context.resources.getColor(
                    R.color.primary_blue,
                    null
                )
            )
            ranking.setTextColor(Color.WHITE)
            all.background =
                context.resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
            all.setTextColor(context.resources.getColor(R.color.font_light, null))
            benefit.background =
                context.resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
            benefit.setTextColor(context.resources.getColor(R.color.font_light, null))

        }

        R.id.game_list_benefit_button -> {

            benefit.setBackgroundColor(
                context.resources.getColor(
                    R.color.primary_blue,
                    null
                )
            )
            benefit.setTextColor(Color.WHITE)
            all.background =
                context.resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
            all.setTextColor(context.resources.getColor(R.color.font_light, null))
            ranking.background =
                context.resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
            ranking.setTextColor(context.resources.getColor(R.color.font_light, null))

        }

        }

    }


}

class GameInfoAdapterViewHolder(itemView: View, private val context: Context) :
    RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val TAG = "GameInfoAdapterViewHold"
    }


    fun bindWithView() {
        Log.d(TAG, "bindWithView: 뷰 바인더")
    }
}