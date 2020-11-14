package io.motoo.www.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.motoo.www.R
import io.motoo.www.others.onMyTextChanged
import kotlinx.android.synthetic.main.find_i_d_fragment.view.*
import kotlinx.android.synthetic.main.find_i_d_fragment.view.spinner
import kotlinx.android.synthetic.main.fragment_sign_up_verification.view.*

class FindIDFragment : Fragment() {


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.find_i_d_fragment, container, false)

        v.phone_num.onMyTextChanged {
            if (it?.count()!! == 11) {
                v.email_send_to_phone_button.isEnabled = true
                v.email_send_to_phone_button.background = resources.getDrawable(R.drawable.bg_round_brand_4dp, null)
            } else {
                v.email_send_to_phone_button.isEnabled = false
                v.email_send_to_phone_button.background = resources.getDrawable(R.drawable.bg_round_gray_300_4dp, null)

            }
        }


        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.country_num,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            v.spinner.adapter = adapter
        }

        return v
    }


}