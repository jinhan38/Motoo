package io.motoo.www.common

import androidx.appcompat.app.AppCompatActivity
import io.motoo.www.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setupLister()

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}