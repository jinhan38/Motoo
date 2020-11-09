package io.motoo.www.ui.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.motoo.www.R
import io.motoo.www.common.BaseActivity

class GameRankingDetailInfo : BaseActivity() {

    override fun setupLister() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_ranking_detail_info)
        setupLister()
    }
}