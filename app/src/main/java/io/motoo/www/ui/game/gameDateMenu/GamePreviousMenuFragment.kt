package io.motoo.www.ui.game.gameDateMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.motoo.www.R

class GamePreviousMenuFragment : Fragment() {

    companion object {

        private const val TAG = "GameFragment"

        @Volatile
        private var instance: GamePreviousMenuFragment? = null

        fun getInstance(): GamePreviousMenuFragment =
            instance ?: synchronized(this) {
                instance ?: GamePreviousMenuFragment().also {
                    instance = it
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_previous_menu, container, false)
    }

}