package io.motoo.www.ui.mypage.setting.faq

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.set
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R
import io.motoo.www.databinding.FragmentFAQBinding
import io.motoo.www.others.LinearLayoutManagerWithSmoothScroller
import io.motoo.www.others.Utils
import io.motoo.www.ui.Bottom
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FAQFragment : Fragment(), io.motoo.www.ui.mypage.setting.faq.ItemClickListener {

    lateinit var b: FragmentFAQBinding
    lateinit var faqAdapter: FAQRecyclerViewAdapter
    private var dataList = ArrayList<String>()
    private var page: Int = 1
    private var limit: Int = 15

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        b = DataBindingUtil.inflate(inflater, R.layout.fragment_f_a_q, container, false)
        b.backButton.setOnClickListener {
            Bottom.context.onBackPressed()
        }

        recyclerViewSetting()
        getData(page, limit)


        return b.root
    }

    private fun getData(page: Int, limit: Int) {
        //initialize retrofit
//        var retrofit = Retrofit.Builder()
//            .baseUrl("")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

        //인터페이스 연결
    }


    fun recyclerViewSetting() {

        dataList.clear()
        for (i in 0..15) {
            dataList.add(i.toString())
        }

        b.faqRecyclerView.apply {
            var layoutManager =
                LinearLayoutManagerWithSmoothScroller(activity, LinearLayoutManager.VERTICAL, false)
            dataList.reverse()
            this.layoutManager = layoutManager
            setHasFixedSize(true)

            faqAdapter = FAQRecyclerViewAdapter(this@FAQFragment, this)
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

class FAQRecyclerViewAdapter(
    private val itemClickListener: io.motoo.www.ui.mypage.setting.faq.ItemClickListener,
    val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<FAQRecyclerViewAdapter.FAQAdapterViewHolder>() {

    private var dataList = ArrayList<String>()
    private var sparseArray = SparseArray<Boolean>()
    private var itemView = HashMap<Int, ConstraintLayout>()
    private var imageView = HashMap<Int, ImageView>()


    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQAdapterViewHolder {
        return FAQAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.faq_recyclerview_item, parent, false),
            itemClickListener,
            recyclerView,
            dataList
        )
    }


    override fun onBindViewHolder(holder: FAQAdapterViewHolder, position: Int) {
        sparseArray.put(position, false)
        holder.bind(sparseArray = sparseArray, itemView = itemView, imageView = imageView)

    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }


    class FAQAdapterViewHolder(
        itemView: View,
        private val itemClickListener: io.motoo.www.ui.mypage.setting.faq.ItemClickListener,
        val recyclerView: RecyclerView,
        val dataList: ArrayList<String>
    ) :
        RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        var itemButton: ConstraintLayout = itemView.findViewById(R.id.item_button)
        var faq_item_child: ConstraintLayout = itemView.findViewById(R.id.faq_item_child)
        var item_arrow: ImageView = itemView.findViewById(R.id.item_arrow)


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(
            sparseArray: SparseArray<Boolean>,
            itemView: HashMap<Int, ConstraintLayout>,
            imageView: HashMap<Int, ImageView>
        ) {
            itemView[adapterPosition] = faq_item_child
            imageView[adapterPosition] = item_arrow

            titleTextView.text = dataList[adapterPosition]

            if (!sparseArray[adapterPosition]) {
                collapseItem(
                    itemView[adapterPosition]!!,
                    imageView[adapterPosition]!!
                )
            } else {
                expandItem(
                    itemView[adapterPosition]!!,
                    imageView[adapterPosition]!!
                )
            }


            itemButton.setOnClickListener {
                TransitionManager.beginDelayedTransition(faq_item_child, AutoTransition())

                when (faq_item_child.visibility) {
                    View.VISIBLE -> {
                        sparseArray[adapterPosition] = false
                        collapseItem(faq_item_child, item_arrow)
                    }
                    View.GONE -> {
                        sparseArray[adapterPosition] = true
                        recyclerView.smoothScrollToPosition(adapterPosition)
                        expandItem(faq_item_child, item_arrow)
                    }
                }

            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun expandItem(view: View, imageView: ImageView) {
            view.visibility = View.VISIBLE
            imageView.apply {
                setImageDrawable(
                    this.context.resources.getDrawable(
                        R.drawable.ic_arrow_up,
                        null
                    )
                )
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun collapseItem(view: View, imageView: ImageView) {
            view.visibility = View.GONE
            imageView.apply {
                setImageDrawable(
                    this.context.resources.getDrawable(
                        R.drawable.ic_arrow_up,
                        null
                    )
                )
            }
        }

    }

}