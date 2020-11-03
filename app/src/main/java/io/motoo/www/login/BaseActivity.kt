package io.motoo.www.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.motoo.www.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}