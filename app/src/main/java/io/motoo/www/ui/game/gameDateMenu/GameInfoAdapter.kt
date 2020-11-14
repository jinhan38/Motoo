package io.motoo.www.ui.game.gameDateMenu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
                headerHolder.setupListener()
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

    companion object{
        private const val TAG = "GameInfoAdapter"
    }

    val context = headerView.context
    val all = headerView.game_list_all_button
    val ranking = headerView.game_list_ranking_button
    val benefit = headerView.game_list_benefit_button
    val 연습경기 = headerView.연습경기
    val 오천원 = headerView.오천원
    val 만원 = headerView.만원
    val 이만원 = headerView.이만원

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setupListener() {
        all.setOnClickListener(this)
        ranking.setOnClickListener(this)
        benefit.setOnClickListener(this)
        연습경기.setOnClickListener(this)
        오천원.setOnClickListener(this)
        만원.setOnClickListener(this)
        이만원.setOnClickListener(this)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onClick(p0: View?) {


        Log.d("TAG", "onClick: 클릭")

        when (p0?.id) {

            R.id.game_list_all_button -> {

                all.setBackgroundColor(
                    context.resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                all.setTextColor(Color.WHITE)
                ranking.background =
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
                ranking.setTextColor(context.resources.getColor(R.color.font_light, null))
                benefit.background =
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
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
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
                all.setTextColor(context.resources.getColor(R.color.font_light, null))
                benefit.background =
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
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
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
                all.setTextColor(context.resources.getColor(R.color.font_light, null))
                ranking.background =
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
                ranking.setTextColor(context.resources.getColor(R.color.font_light, null))

            }

            R.id.연습경기 -> {
                priceTabInit()
                연습경기.apply {
                    background =
                        context.resources.getDrawable(R.drawable.bg_blue_round_16dp, null)
                    setTextColor(context.resources.getColor(R.color.primary_blue, null))
                    typeface = Typeface.DEFAULT_BOLD
                }
            }

            R.id.오천원 -> {
                priceTabInit()
                오천원.apply {
                    background =
                        context.resources.getDrawable(R.drawable.bg_blue_round_16dp, null)
                    setTextColor(context.resources.getColor(R.color.primary_blue, null))
                    typeface = Typeface.DEFAULT_BOLD
                }
            }

            R.id.만원 -> {
                priceTabInit()
                만원.apply {
                    background =
                        context.resources.getDrawable(R.drawable.bg_blue_round_16dp, null)
                    setTextColor(context.resources.getColor(R.color.primary_blue, null))
                    typeface = Typeface.DEFAULT_BOLD
                }
            }

            R.id.이만원 -> {
                priceTabInit()
                이만원.apply {
                    background =
                        context.resources.getDrawable(R.drawable.bg_blue_round_16dp, null)
                    setTextColor(context.resources.getColor(R.color.primary_blue, null))
                    typeface = Typeface.DEFAULT_BOLD
                }
            }


        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun priceTabInit() {

        var textView = ArrayList<TextView>()
        textView.add(연습경기)
        textView.add(오천원)
        textView.add(만원)
        textView.add(이만원)
        Log.d(TAG, "priceTabInit: size : ${textView.size}")
        for (i in 0..(textView.size - 1)) {
            Log.d(TAG, "priceTabInit: i : $i")
            textView[i].apply {
                background =
                    context.resources.getDrawable(R.drawable.bg_basic_gray_round_16dp, null)
                setTextColor(context.resources.getColor(R.color.gray_500, null))
                typeface = Typeface.DEFAULT
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