package io.motoo.www.signUp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.motoo.www.R
import io.motoo.www.others.replaceFragment
import kotlinx.android.synthetic.main.fragment_sign_up_terms.view.*

class SignUpTerms : Fragment(), View.OnClickListener {

    lateinit var v: View
    var checkBoxCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        v = inflater.inflate(R.layout.fragment_sign_up_terms, container, false)
        v.checkbox_all.setOnCheckedChangeListener { compoundButton, b ->
            checkBoxAllListener(b)
        }


        v.checkbox_service.setOnClickListener(this)
        v.checkbox_privacy.setOnClickListener(this)
        v.checkbox_marketing.setOnClickListener(this)
        v.checkbox_marketing_privacy.setOnClickListener(this)


        v.button_next.setOnClickListener {
            Log.d(TAG, "onCreateView: 버튼 클릭")
            moveToNext()
        }


        v.back_button.setOnClickListener {
            activity?.onBackPressed()
        }

        return v
    }

    private fun checkBoxAllListener(boolean: Boolean) {
        if (boolean) {
            v.checkbox_service.isChecked = true
            v.checkbox_privacy.isChecked = true
            v.checkbox_marketing.isChecked = true
            v.checkbox_marketing_privacy.isChecked = true

            nextButtonOpen()

        } else {

            v.checkbox_service.isChecked = false
            v.checkbox_privacy.isChecked = false
            v.checkbox_marketing.isChecked = false
            v.checkbox_marketing_privacy.isChecked = false

            nextButtonClose()

        }

    }

    private fun moveToNext() {


        if (v.checkbox_service.isChecked && v.checkbox_privacy.isChecked) {

            SignUpProfile().replaceFragment(R.id.fragment, requireActivity())
            
        } else {
            Toast.makeText(activity, "필수 약관에 동의해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(p0: View?) {
        Log.d(TAG, "onClick: 버튼 뷰클릭")
        when (p0) {
            v.checkbox_service -> checkBoxControl()
            v.checkbox_privacy -> checkBoxControl()
            v.checkbox_marketing -> checkBoxControl()
            v.checkbox_marketing_privacy -> checkBoxControl()

        }
    }

    private fun nextButtonOpen() {
        v.button_next.apply {
//            setTextColor(resources.getColor(R.color.white, null))
            setBackgroundColor(resources.getColor(R.color.primary_blue, null))
            isEnabled = true
        }
    }

    private fun nextButtonClose() {
        v.button_next.apply {
//            setTextColor(resources.getColor(R.color.black, null))
            setBackgroundColor(resources.getColor(R.color.soft_gray, null))
            isEnabled = false
        }
    }

    private fun checkBoxControl() {
        Log.d(TAG, "checkBoxControl: 진입")
        if (v.checkbox_service.isChecked && v.checkbox_privacy.isChecked && v.checkbox_marketing.isChecked && v.checkbox_marketing_privacy.isChecked) {
            nextButtonOpen()
        } else if (v.checkbox_service.isChecked && v.checkbox_privacy.isChecked) {
            nextButtonOpen()
        } else {
            Log.d(TAG, "checkBoxControl: 버튼 클로즈")
            nextButtonClose()
        }
    }


}