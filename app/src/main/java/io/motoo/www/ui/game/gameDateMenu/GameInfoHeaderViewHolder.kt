package io.motoo.www.ui.game.gameDateMenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import kotlinx.android.synthetic.main.game_info_recyclerview_header.view.*


class GameInfoHeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView),

    View.OnClickListener {

    companion object {
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

                typeTabInit()
                priceTabInit()
                typeTabClick(all)
                practiceTabClick(연습경기)

            }

            R.id.game_list_ranking_button -> {

                typeTabInit()
                priceTabInit()
                typeTabClick(ranking)
                practiceTabClick(연습경기)

            }
            R.id.game_list_benefit_button -> {

                typeTabInit()
                typeTabClick(benefit)
                priceTabInit()
                practiceTabClick(연습경기)

            }

            R.id.연습경기 -> {
                priceTabInit()
                practiceTabClick(연습경기)
            }

            R.id.오천원 -> {
                priceTabInit()
                practiceTabClick(오천원)
            }

            R.id.만원 -> {
                priceTabInit()
                practiceTabClick(만원)
            }

            R.id.이만원 -> {
                priceTabInit()
                practiceTabClick(이만원)
            }


        }

    }

    /**
     * 경기 타입 탭 초기화
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun typeTabInit() {

        var textView = ArrayList<TextView>()
        textView.add(all)
        textView.add(ranking)
        textView.add(benefit)

        for (i in 0..(textView.size - 1)) {
            textView[i].apply {
                background =
                    context.resources.getDrawable(R.drawable.bg_border_gray_round_1dp, null)
                setTextColor(context.resources.getColor(R.color.font_light, null))
            }
        }
    }

    /**
     * 타입 탭 클릭 세팅
     */
    private fun typeTabClick(textView: TextView) {

        textView.apply {
            setBackgroundColor(context.resources.getColor(R.color.primary_blue, null))
            setTextColor(Color.WHITE)
        }
    }

    /**
     * 경기 금액 탭 클릭 세팅
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun practiceTabClick(textView: TextView) {

        textView.apply {
            background =
                context.resources.getDrawable(R.drawable.bg_blue_round_16dp, null)
            setTextColor(context.resources.getColor(R.color.primary_blue, null))
            typeface = Typeface.DEFAULT_BOLD
        }
    }


    /**
     * 가격 탭 초기화
     */
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