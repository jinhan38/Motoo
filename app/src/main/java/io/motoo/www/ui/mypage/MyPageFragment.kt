package io.motoo.www.ui.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import io.motoo.www.R
import io.motoo.www.databinding.FragmentMypageBinding
import io.motoo.www.others.replaceFragment
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.mypage.MyPageWithdrawMoney.MyPageWithdrawMoneyFragment
import io.motoo.www.ui.mypage.gameRecord.GameRecordFragment
import io.motoo.www.ui.mypage.setting.SettingFragment


class MyPageFragment : Fragment(), ItemClickListener, View.OnClickListener {


    companion object {

        private const val 경기기록 = "경기기록"
        private const val 출금하기 = "출금하기"

        private const val TAG = "MyPageFragment"

        @Volatile
        private var instance: MyPageFragment? = null


        fun getInstance(): MyPageFragment =
            instance ?: synchronized(this) {
                instance ?: MyPageFragment().also {
                    instance = it
                }
            }
    }

    lateinit var v: View
    lateinit var b: FragmentMypageBinding
    var dataList = ArrayList<String>()
    lateinit var myPageRecyclerViewAdapter: MyPageRecyclerViewAdapter


    private var myUrl = ""
    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        setupListener()
        b.profileImageEdit.setOnClickListener {
            Log.d(TAG, "onCreateView: 편집 클릭")
//            CropImage.activity().start(requireActivity(), this)
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(requireActivity());
        }

        recyclerViewSetting()

        return b.root
    }

    private fun setupListener() {
        b.settingImageView.setOnClickListener(this)
//        b.profileImageEdit.setOnClickListener(this)
    }


    fun recyclerViewSetting() {

        dataList.clear()

        dataList.add("1")
        dataList.add("2")
        dataList.add("3")

        b.myPageRecyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            myPageRecyclerViewAdapter = MyPageRecyclerViewAdapter(this@MyPageFragment)
            myPageRecyclerViewAdapter.addItem(dataList)
            adapter = myPageRecyclerViewAdapter

        }
    }


    override fun onClickListener(position: Int, typeNum: Int) {

        when (typeNum) {
            1 -> {
                GameRecordFragment().replaceFragment(R.id.fragment_container, requireActivity())
            }
            2 -> {

                parentFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace(
                        R.id.fragment_container,
                        MyPageWithdrawMoneyFragment.newInstance(2)
                    )
                    addToBackStack(null)
                }


            }

            3 -> {

                parentFragmentManager.commit {

                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace(
                        R.id.fragment_container,
                        MyPageWithdrawMoneyFragment.newInstance(3)
                    )
                    addToBackStack(null)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
//        Utils.setStatusBarColor(requireActivity(), resources.getColor(R.color.primary_blue, null))
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.settingImageView -> {
                Bottom.context.fragmentChange(SettingFragment())
            }
//            R.id.profile_edit_button -> {
//                Log.d(TAG, "onClick: 편집 클릭")
//                CropImage.activity().start(requireActivity(), this)
//            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val imageUri = result.uri
                b.circleImageView.setImageURI(imageUri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }
}
