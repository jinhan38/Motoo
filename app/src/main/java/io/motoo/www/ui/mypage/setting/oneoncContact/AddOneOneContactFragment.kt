package io.motoo.www.ui.mypage.setting.oneoncContact

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.theartofdev.edmodo.cropper.CropImage
import io.motoo.www.R
import io.motoo.www.databinding.FragmentAddOneOneContactBinding
import io.motoo.www.databinding.FragmentSettingBinding
import io.motoo.www.others.Utils
import io.motoo.www.others.onMyTextChanged
import io.motoo.www.ui.Bottom

class AddOneOneContactFragment : Fragment() {

    companion object {

        @Volatile
        private var instance: AddOneOneContactFragment? = null

        fun getInstance(): AddOneOneContactFragment =
            instance ?: synchronized(this) {
                instance ?: AddOneOneContactFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentAddOneOneContactBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_one_one_contact,
            container,
            false
        )

        b.backButton.setOnClickListener {
            Bottom.context.onBackPressed()
        }

        b.inputOneOneContact.onMyTextChanged {
            if (it.toString().isEmpty()) {
                b.oneOneContactAddButton.background =
                    requireActivity().resources.getDrawable(
                        R.drawable.bg_solid_inactive_round_4dp,
                        null
                    )
                
            } else {
                b.oneOneContactAddButton.background =
                    requireActivity().resources.getDrawable(R.drawable.bg_round_brand_4dp, null)
            }
        }

        b.uploadContactImage.setOnClickListener {

            CropImage.activity().start(requireActivity(), this)
        }

        return b.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val imageUri = result.uri

//                b.circleImageView.setImageURI(imageUri)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}