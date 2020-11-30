package io.motoo.www.ui.market.addEvent

import android.annotation.SuppressLint
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.motoo.www.R

class AddEventRecyclerViewAdapter : RecyclerView.Adapter<AddEventRecyclerViewAdapter.ViewHolder>() {

    var dataList = ArrayList<String>()

    companion object {
        private const val TAG = "AddEventRecyclerViewAda"
    }

    @SuppressLint("UseSparseArrays")
    var sparseArray = SparseArray<Boolean>()


    // onCreateViewHolder에서 inflate하는 부분을 간단하게 하기 위해 추가
    private fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.recyclerview_event_item),
            dataList = dataList,
            sparseArray = sparseArray
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addItem(dataList: ArrayList<String>) {
        this.dataList = dataList
    }


    class ViewHolder(
        itemView: View,
        val dataList: ArrayList<String>,
        val sparseArray: SparseArray<Boolean>
    ) :
        RecyclerView.ViewHolder(itemView) {


        var eventItemTitle = itemView.findViewById<TextView>(R.id.eventItemTitle)
        var eventItemContent = itemView.findViewById<TextView>(R.id.eventItemContent)
        var eventItemFavoriteButton = itemView.findViewById<ImageView>(R.id.eventItemFavoriteButton)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind() {

            if (sparseArray[adapterPosition] == null) {
                sparseArray.put(adapterPosition, false)
            }

            if (sparseArray[adapterPosition] == false) {
                setUnCheckedStar(eventItemFavoriteButton)
            } else {
                setCheckedStar(eventItemFavoriteButton)
            }

            eventItemTitle.text = dataList[adapterPosition]

            Log.d(TAG, "bind: sparseArray : ${sparseArray[adapterPosition]}")
            eventItemFavoriteButton.setOnClickListener {
                if (sparseArray[adapterPosition]) {
                    setUnCheckedStar(it)
                } else {
                    setCheckedStar(it)
                }
            }

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun setCheckedStar(view: View) {
            sparseArray.put(adapterPosition, true)
            eventItemFavoriteButton.setImageDrawable(
                view.context.resources.getDrawable(
                    R.drawable.ic_star_checked,
                    null
                )
            )
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun setUnCheckedStar(view: View) {
            sparseArray.put(adapterPosition, false)
            eventItemFavoriteButton.setImageDrawable(
                view.context.resources.getDrawable(
                    R.drawable.ic_star_unchecked,
                    null
                )
            )
        }

    }

}