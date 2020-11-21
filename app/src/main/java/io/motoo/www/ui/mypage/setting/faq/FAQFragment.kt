package io.motoo.www.ui.mypage.setting.faq

import android.graphics.Color
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import io.motoo.www.databinding.FragmentFAQBinding
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import io.motoo.www.ui.mypage.setting.notice.NoticeFragment

class FAQFragment : Fragment(), io.motoo.www.ui.mypage.setting.faq.ItemClickListener{


    companion object {

        @Volatile
        private var instance: FAQFragment? = null

        fun getInstance(): FAQFragment =
            instance ?: synchronized(this) {
                instance ?: FAQFragment().also {
                    instance = it
                }
            }
    }

    lateinit var b: FragmentFAQBinding
    lateinit var faqAdapter : FAQRecyclerViewAdapter
    private var dataList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_f_a_q, container, false)
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

        b.faqRecyclerView.apply {
            var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            dataList.reverse()
            this.layoutManager = layoutManager
            setHasFixedSize(true)
            faqAdapter = FAQRecyclerViewAdapter(this@FAQFragment)
            faqAdapter.addItem(dataList)
            adapter = faqAdapter

        }
    }

    override fun onStart() {
        super.onStart()
        Utils.setStatusBarColorWhite(requireActivity(), Color.WHITE)
    }

    override fun onClickListener(position: Int) {

    }

}

    class FAQRecyclerViewAdapter(private val itemClickListener: io.motoo.www.ui.mypage.setting.faq.ItemClickListener) :
    RecyclerView.Adapter<FAQRecyclerViewAdapter.FAQAdapterViewHolder>() {

    private var dataList = ArrayList<String>()


    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQAdapterViewHolder {
        return FAQAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.faq_recyclerview_item, parent, false),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: FAQAdapterViewHolder, position: Int) {

        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    class FAQAdapterViewHolder(
        itemView: View,
        private val itemClickListener: io.motoo.www.ui.mypage.setting.faq.ItemClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {

        var itemButton: ImageView = itemView.findViewById(R.id.item_button)
        var faq_item_child: ConstraintLayout = itemView.findViewById(R.id.faq_item_child)

        fun bind() {
            itemButton.setOnClickListener {

                when(faq_item_child.visibility){
                    View.VISIBLE ->{
                        TransitionManager.beginDelayedTransition(faq_item_child, AutoTransition())
                        faq_item_child.visibility = View.GONE
                    }
                    View.GONE ->{
                        TransitionManager.beginDelayedTransition(faq_item_child, AutoTransition())
                        faq_item_child.visibility = View.VISIBLE
                    }

                }
                Log.d("TAG", "bind: 클릭함")
                itemClickListener.onClickListener(adapterPosition)
            }
        }
    }

}