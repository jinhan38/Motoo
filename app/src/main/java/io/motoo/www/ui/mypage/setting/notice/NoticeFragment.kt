package io.motoo.www.ui.mypage.setting.notice

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import io.motoo.www.databinding.FragmentNoticeBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom


class NoticeFragment : Fragment(), ItemClickListener {

    companion object {

        @Volatile
        private var instance: NoticeFragment? = null

        fun getInstance(): NoticeFragment =
            instance ?: synchronized(this) {
                instance ?: NoticeFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentNoticeBinding
    lateinit var noticeAdapter: NoticeRecyclerViewAdapter
    private var dataList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_notice, container, false)

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

        b.noticeRecyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            dataList.reverse()
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            noticeAdapter = NoticeRecyclerViewAdapter(this@NoticeFragment)
            noticeAdapter.addItem(dataList)
            adapter = noticeAdapter

        }
    }

    override fun onClickListener(position: Int) {
        Bottom.context.fragmentChange(NoticeDetailFragment.getInstance())
    }


    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

}

class NoticeRecyclerViewAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NoticeRecyclerViewAdapter.NoticeViewHolder>() {

    private var dataList = ArrayList<String>()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        context = parent.context
        return NoticeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notice_recyclerview_item, parent, false),
            itemClickListener
        )

    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }


    class NoticeViewHolder(itemView: View, private var itemClickListener: ItemClickListener) :
        RecyclerView.ViewHolder(itemView) {


        private var noticeView: RelativeLayout = itemView.findViewById(R.id.notice_item)
        var imageView: ImageView = itemView.findViewById(R.id.notice_item_imageView)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(context: Context) {


            val drawable = context.getDrawable(R.drawable.bg_round_transparent_4dp)
            imageView.background = drawable
            imageView.clipToOutline = true

            noticeView.setOnClickListener {
                Log.d("TAG", "bind: 클릭함")
                itemClickListener.onClickListener(adapterPosition)
            }
        }
    }


}