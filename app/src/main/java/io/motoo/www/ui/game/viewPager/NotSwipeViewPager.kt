//package io.motoo.www.ui.game.viewPager
//
//import android.R
//import android.content.Context
//import android.content.res.TypedArray
//import android.util.AttributeSet
//import android.view.MotionEvent
//import androidx.core.view.MotionEventCompat
//import androidx.viewpager.widget.ViewPager
//
//
//class NotSwipeViewPager(context: Context, attrs: AttributeSet) : ViewPager(context) {
//
//
//    init {
//     //   https://stackoverflow.com/questions/9650265/how-do-disable-paging-by-swiping-with-finger-in-viewpager-but-still-be-able-to-s/13437997#13437997
//
////        val a: TypedArray = context.obtainStyledAttributes(attrs, R.style.MyViewPager)
//    }
//
////    private var swipeEnabled = false
////
////    override fun onTouchEvent(event: MotionEvent): Boolean {
////        return when (swipeEnabled) {
////            true -> super.onTouchEvent(event)
////            false -> false
////        }
////    }
////
////    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
////        return when (swipeEnabled) {
////            true -> super.onInterceptTouchEvent(event)
////            false -> false
////        }
////    }
////
////    fun setSwipePagingEnabled(swipeEnabled: Boolean) {
////        this.swipeEnabled = swipeEnabled
////    }
//
//    var isSwipe: Boolean = false
//
//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//
//
//        return if (isSwipe) {
//
//            super.onInterceptTouchEvent(ev)
//
//        } else {
//
//            if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
//
//            } else {
//
//                if (super.onInterceptTouchEvent(ev)) {
//                    super.onTouchEvent(ev)
//                }
//            }
//
//            false
//
//        }
//
//    }
//
//    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        return if (isSwipe) {
//            super.onTouchEvent(ev);
//        } else {
//            MotionEventCompat.getActionMasked(ev) != MotionEvent.ACTION_MOVE && super.onTouchEvent(
//                ev
//            );
//        }
//    }
//
//    fun setPagingEnabled(enabled: Boolean) {
//        this.isSwipe = enabled
//
//    }
//}