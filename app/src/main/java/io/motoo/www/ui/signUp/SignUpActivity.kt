package io.motoo.www.ui.signUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.motoo.www.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}