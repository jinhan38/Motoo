package io.motoo.www.ui.game

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.common.BaseActivity
import io.motoo.www.databinding.ActivityGameRankingDetailInfoBinding
import io.motoo.www.others.Utils

class GameRankingDetailInfoActivity : BaseActivity() {

    lateinit var b: ActivityGameRankingDetailInfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_game_ranking_detail_info)


        b.backButton.setOnClickListener {
            onBackPressed()
        }

        Utils.textColorChange(
            b.titleTextViewFirst, "자산", b.titleTextViewFirst.text.toString(),
            resources.getColor(R.color.primary_blue, null)
        )

        Utils.textColorChange(
            b.titleTextViewSecond, "투자!", b.titleTextViewSecond.text.toString(),
            resources.getColor(R.color.primary_blue, null)
        )

        Utils.textColorChange(
            b.winText, "우승", b.winText.text.toString(),
            resources.getColor(R.color.primary_blue, null)
        )

        Utils.textColorChange(
            b.secondWinText, "준우승", b.secondWinText.text.toString(),
            resources.getColor(R.color.primary_blue, null)
        )

        Utils.textColorChange(
            b.explainSecond, "예시 :", b.explainSecond.text.toString(),
            resources.getColor(R.color.primary_blue, null)
        )

        Utils.textColorChange(
            b.explainThird, "예시 :", b.explainThird.text.toString(),
            resources.getColor(R.color.primary_blue, null)
        )
    }

    override fun setupLister() {

    }


}