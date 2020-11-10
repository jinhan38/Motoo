package io.motoo.www.ui.game.gameDateMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.motoo.www.R

class GameExpectedFragment : Fragment() {

    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: GameExpectedFragment? = null

        fun getInstance(): GameExpectedFragment =
            instance ?: synchronized(this) {
                instance ?: GameExpectedFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_expected, container, false)
    }

}