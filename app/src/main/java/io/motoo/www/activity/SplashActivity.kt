package io.motoo.www.activity

import android.content.Intent
import android.os.Bundle
import io.motoo.www.R
import io.motoo.www.common.BaseActivity
import io.motoo.www.ui.login.LoginActivity
import io.motoo.www.ui.signUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun setupLister() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        start_button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        sign_up_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}