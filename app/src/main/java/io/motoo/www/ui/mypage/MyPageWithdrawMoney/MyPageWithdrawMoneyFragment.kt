package io.motoo.www.ui.mypage.MyPageWithdrawMoney

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import io.motoo.www.R
import io.motoo.www.databinding.FragmentMyPageWithdrawMoneyBinding
import io.motoo.www.others.NumberTextWatcher
import io.motoo.www.others.Utils
import io.motoo.www.others.onMyTextChanged
import kotlinx.android.synthetic.main.fragment_sign_up_verification.view.*
import java.lang.Exception

private const val WITHDRAW_TYPE = "withdraw_type"


class MyPageWithdrawMoneyFragment : Fragment() {

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

    private var withdraw_type: Int? = null
    private var withDrawMoney: Boolean = false
    private var withDrawBank: Boolean = false
    private var withDrawNum: Boolean = false
    private var withDrawOwner: Boolean = false


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


        setTitle()

        setBankListSpinner()

        Utils.textColorChange(
            b.explainTextView, "평일 오후 7시", getString(R.string.explainText_1),
            resources.getColor(R.color.primary_blue, null)
        )



        b.inputWithdrawMoney.addTextChangedListener(NumberTextWatcher(b.inputWithdrawMoney))
        b.inputWithdrawMoney.onMyTextChanged { text ->

            if (text!!.isNotEmpty()) {

                var strText = text.toString().replace(",", "")
                    .replace("원", "").replace(" ", "")

                Log.d(TAG, "onCreateView: $strText")
                if (strText.toInt() < 5000) {
                    b.withDrawMoneyErrorText.apply {
                        this.visibility = View.VISIBLE
                        this.text = "5,000원 이상부터 출금이 가능합니다."
                    }
                    Utils.editTextUnderLineRed(requireActivity(), b.inputWithdrawMoney)
                    withDrawMoney = false

                } else if ((strText.toInt() % 5000) != 0) {
                    b.withDrawMoneyErrorText.apply {
                        this.visibility = View.VISIBLE
                        this.text = "5,000원 단위로 출금할 수 있습니다."
                    }
                    Utils.editTextUnderLineRed(requireActivity(), b.inputWithdrawMoney)
                    withDrawMoney = false

                } else {
                    b.withDrawMoneyErrorText.visibility = View.INVISIBLE
                    Utils.editTextUnderLineGray(requireActivity(), b.inputWithdrawMoney)
                    withDrawMoney = true
                }

                try {
                } catch (e: Exception) {
                    Log.d(TAG, "onCreateView: error : $e")
                }
            } else {
                b.withDrawMoneyErrorText.visibility = View.INVISIBLE
                Utils.editTextUnderLineGray(requireActivity(), b.inputWithdrawMoney)
            }


            withDrawButtonActive()
        }



        b.backListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                withDrawBank = p2 != 0
                    withDrawButtonActive()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        b.bankNum.onMyTextChanged {

            when {
                it.toString().isNotEmpty() -> {
                    withDrawNum = true
                }
                it.toString().isEmpty() -> {
                    withDrawNum = false
                }
            }
            withDrawButtonActive()

        }

        b.bankOwner.onMyTextChanged {

            when {
                it.toString().isNotEmpty() -> {
                    withDrawOwner = true
                    Log.d(TAG, "onCreateView: 예금주 있음")
                }
                it.toString().length >= 2 -> {
                    withDrawOwner = false
                    Log.d(TAG, "onCreateView: 예금주 없음")
                }
            }

            withDrawButtonActive()
        }

        return b.root

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun withDrawButtonActive() {
        Log.d(TAG, "withDrawButtonActive: 진입")
        if (withDrawMoney && withDrawBank && withDrawNum && withDrawOwner) {
            b.withDrawButton.background =
                requireActivity().resources.getDrawable(R.drawable.bg_round_brand_4dp, null)
        } else {
            b.withDrawButton.background =
                requireActivity().resources.getDrawable(R.drawable.bg_round_gray_300_4dp, null)

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setTitle() {

        b.toolbar.navigationIcon = activity?.resources?.getDrawable(R.drawable.ic_arrow_left, null)
        if (withdraw_type == 2) {
            b.title.text = "상금출금"
            b.bigTitle.text = "상금출금"

        } else {
            b.title.text = "캐시출금"
            b.bigTitle.text = "캐시출금"

        }
    }

    private fun setBankListSpinner() {


        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.bankList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            b.backListSpinner.adapter = adapter
        }

    }

    override fun onStop() {
        super.onStop()
        Utils.setStatusBarColorBlue(
            requireActivity(),
            resources.getColor(R.color.primary_blue, null)
        )
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}