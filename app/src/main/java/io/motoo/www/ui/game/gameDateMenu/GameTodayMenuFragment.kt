package io.motoo.www.ui.game.gameDateMenu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentGameTodayMenuBinding
import io.motoo.www.ui.game.GameRankingDetailInfo

class GameTodayMenuFragment : Fragment(), View.OnClickListener {

    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: GameTodayMenuFragment? = null

        fun getInstance(): GameTodayMenuFragment =
            instance ?: synchronized(this) {
                instance ?: GameTodayMenuFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentGameTodayMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_game_today_menu, container, false)
        setupListener()
        
        return b.root
    }


    fun setupListener() {

        b.gameListAllButton.setOnClickListener(this)
        b.gameListRankingButton.setOnClickListener(this)
        b.gameListBenefitButton.setOnClickListener(this)
        b.goDetailRankingInfo.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.goDetailRankingInfo -> {
                activity?.startActivity(Intent(activity, GameRankingDetailInfo::class.java))
                activity?.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            R.id.game_list_all_button -> {

                b.gameListAllButton.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                b.gameListAllButton.setTextColor(Color.WHITE)
                b.gameListRankingButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListRankingButton.setTextColor(resources.getColor(R.color.font_light, null))
                b.gameListBenefitButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListBenefitButton.setTextColor(resources.getColor(R.color.font_light, null))
            }

            R.id.game_list_ranking_button -> {

                b.gameListRankingButton.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                b.gameListRankingButton.setTextColor(Color.WHITE)
                b.gameListAllButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListAllButton.setTextColor(resources.getColor(R.color.font_light, null))
                b.gameListBenefitButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListBenefitButton.setTextColor(resources.getColor(R.color.font_light, null))

            }

            R.id.game_list_benefit_button -> {

                b.gameListBenefitButton.setBackgroundColor(
                    resources.getColor(
                        R.color.primary_blue,
                        null
                    )
                )
                b.gameListBenefitButton.setTextColor(Color.WHITE)
                b.gameListAllButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListAllButton.setTextColor(resources.getColor(R.color.font_light, null))
                b.gameListRankingButton.background =
                    resources.getDrawable(R.drawable.bg_border_gray_1dp, null)
                b.gameListRankingButton.setTextColor(resources.getColor(R.color.font_light, null))

            }
        }
    }

}