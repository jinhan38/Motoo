package io.motoo.www.others

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import io.motoo.www.R
import java.text.DecimalFormat
import java.util.*
import java.util.regex.Pattern


class Utils {

    companion object {
        var fragmentStack: Stack<Fragment>? = null

        private const val TAG = "Utils"

        fun replaceFragment(fragment: Fragment, containerView: Int, activity: FragmentActivity) {

//            var activity = fragment.activity
            var fragment = fragment
            Log.d(
                ContentValues.TAG,
                "replaceFragment: 진입 fragment : $fragment, containerView : $containerView"
            )
            activity.supportFragmentManager?.commit {
                setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                replace(containerView, fragment)
                addToBackStack(null)
            }
        }

        fun isValidEmail(email: String?): Boolean {
            var rValue = false
            val regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
            val p = Pattern.compile(regex)
            val m = p.matcher(email)
            if (m.matches()) {
                rValue = true
            }
            return rValue
        }


        fun setTextColor(
            context: Context, text: String, textView: TextView, colorString: Int,
            start: Int, end: Int
        ) {

            val ssb = SpannableStringBuilder(text)
            ssb.setSpan(
                ForegroundColorSpan(colorString), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = ssb
        }


        fun textColorChange(
            textView: TextView,
            keyword: String,
            content: String,
            changeColor: Int
        ) {


            val highlighted = "<font color='#403fff'>$keyword</font>"
//        val keyword = "사용자의 개인정보를 철저히 보호"
            Log.e(TAG, "textHighlight: keyword : $keyword")
//                    String highlighted = "<font color='red'>" + keyword + "</font>";
//        String highlighted =  "<span style=\"background-color:yellow;\">" + keyword +  "</span>";
            if (content.contains(keyword)) {
                Log.d(TAG, "textColorChange: $content")
                textView.text = Html.fromHtml(content.replace(keyword, highlighted), 0)
            }
        }

        fun setStatusBarColorWhite(activity: Activity, color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.statusBarColor = color
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }


        fun setStatusBarColorBlue(activity: Activity, color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                activity.window.statusBarColor = color
                activity.window.decorView.systemUiVisibility = Configuration.UI_MODE_NIGHT_MASK
            }
        }


        fun GetNumFormat(num: Int): String? {
            val df = DecimalFormat("#,###")
            return if (num >= 10000) {
                val bilTemp = num / 10000
                val bil = df.format(bilTemp.toLong()) + "억"
                val milTemp = num - bilTemp * 10000
                if (milTemp > 0) {
                    val mil = df.format(milTemp.toLong()) + " 만원"
                    "$bil $mil"
                } else {
                    bil + "원"
                }
            } else {
                df.format(num.toLong()) + " 만원"
            }
        }


        fun GetNumFormatWon(price: Int): String? {

            val myFormatter = DecimalFormat("###,###")
            val formattedStringPrice: String = myFormatter.format(price)
            return "$formattedStringPrice"
        }


    }


}