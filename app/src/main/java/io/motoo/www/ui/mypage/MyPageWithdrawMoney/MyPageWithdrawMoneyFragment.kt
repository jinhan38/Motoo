package io.motoo.www.ui.mypage.MyPageWithdrawMoney

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentMyPageWithdrawMoneyBinding
import io.motoo.www.others.NumberTextWatcher
import io.motoo.www.others.Utils
import io.motoo.www.others.onMyTextChanged
import java.lang.Exception

private const val WITHDRAW_TYPE = "withdraw_type"


class MyPageWithdrawMoneyFragment : Fragment() {
    private var withdraw_type: Int? = null

    companion object {

        private const val TAG = "MyPageWithdrawMoneyFrag"

        @JvmStatic
        fun newInstance(withdrawType: Int) =
            MyPageWithdrawMoneyFragment().apply {
                arguments = Bundle().apply {
                    putInt(WITHDRAW_TYPE, withdrawType)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            withdraw_type = it.getInt(WITHDRAW_TYPE)
        }
    }

    lateinit var b: FragmentMyPageWithdrawMoneyBinding

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_page_withdraw_money,
            container,
            false
        )

        Log.d(TAG, "onCreateView: withdraw_type : $withdraw_type")
        b.toolbar.navigationIcon = activity?.resources?.getDrawable(R.drawable.ic_arrow_left, null)
        if (withdraw_type == 2) {
            b.title.text = "상금출금"
            b.bigTitle.text = "상금출금"

        } else {
            b.title.text = "캐시출금"
            b.bigTitle.text = "캐시출금"

        }

        b.inputWithdrawMoney.addTextChangedListener(NumberTextWatcher(b.inputWithdrawMoney))
        b.inputWithdrawMoney.onMyTextChanged { text ->
            if (text!!.isNotEmpty()) {
                Log.d(TAG, "onCreateView: $text")


                var strText = text.toString().replace(",", "").replace("원", "").replace(" ", "")
                if (strText.toInt() < 5000){
                    Log.d(TAG, "onCreateView: 5천원 미만")
                }

                try {
                } catch (e: Exception) {
                    Log.d(TAG, "onCreateView: error : $e")
                }
            }
        }

        return b.root

    }

    override fun onStop() {
        super.onStop()
        Utils.setStatusBarColorBlue(requireActivity(), resources.getColor(R.color.primary_blue, null))
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}