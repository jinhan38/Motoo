package io.motoo.www.ui.mypage.setting.oneoncContact

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import io.motoo.www.databinding.FragmentOneOneContactBinding
import io.motoo.www.databinding.FragmentViewMyQuestionBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.mypage.setting.notice.NoticeRecyclerViewAdapter

class ViewMyQuestionFragment : Fragment(), FooterClickListener {


    companion object {


        @Volatile
        private var instance: ViewMyQuestionFragment? = null

        fun getInstance(): ViewMyQuestionFragment =
            instance ?: synchronized(this) {
                instance ?: ViewMyQuestionFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentViewMyQuestionBinding

    lateinit var viewMyQuestionAdapter: ViewMyQuestionAdapter
    private var dataList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_view_my_question, container, false)

        b.backButton.setOnClickListener {
            Bottom.context.onBackPressed()
        }

        recyclerViewSetting()

        return b.root
    }


    fun recyclerViewSetting() {


        dataList.add("4")
        dataList.add("5")
        dataList.add("6")

        b.myQuestionRecyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            dataList.reverse()
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            viewMyQuestionAdapter = ViewMyQuestionAdapter(this@ViewMyQuestionFragment)
            viewMyQuestionAdapter.addItem(dataList)
            adapter = viewMyQuestionAdapter

        }
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

    override fun onFooterClickListener() {
        Toast.makeText(requireActivity(), "더보기 클릭", Toast.LENGTH_SHORT).show()
    }
}


class ViewMyQuestionAdapter(private val footerClickListener: FooterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var ITEM_VIEW = 0
    private var FOOTER_VIEW = 1
    private var dataList = ArrayList<String>()


    class ViewMyQuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var profileImage = itemView.findViewById<ImageView>(R.id.profile_image)
        var nickName = itemView.findViewById<TextView>(R.id.nickName)
        var question_date = itemView.findViewById<TextView>(R.id.question_date)
        var question_content = itemView.findViewById<TextView>(R.id.question_content)
        var answer_date = itemView.findViewById<TextView>(R.id.answer_date)
        var answer_content = itemView.findViewById<TextView>(R.id.answer_content)

        fun bind() {

        }
    }

    class ViewMyQuestionViewHolderFooter(
        itemView: View,
        private val footerClickListener: FooterClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        val view = itemView.findViewById<RelativeLayout>(R.id.view_more_footer)


        fun bind() {
            view.setOnClickListener {
                footerClickListener.onFooterClickListener()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (position == dataList.size) {
            FOOTER_VIEW
        } else {
            ITEM_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW -> {
                ViewMyQuestionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.my_question_recyclerview_item, parent, false)
                )
            }

            else -> {
                ViewMyQuestionViewHolderFooter(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.my_question_recyclerview_footer, parent, false),
                    footerClickListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewMyQuestionViewHolderFooter) {
            (holder).bind()
        } else {
            (holder as ViewMyQuestionViewHolder).bind()
        }

    }

    override fun getItemCount(): Int {
        return dataList.size + 1
    }

    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }

}