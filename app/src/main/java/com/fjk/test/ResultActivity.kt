package com.fjk.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fjk.test.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val result = intent.getStringExtra("result")
        mBinding.tvResult.text = result.toString()
    }

}
