package com.fjk.test

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fjk.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initData()
        initListener()
    }

    private fun initData() {
        setAmount("")
        setTime("")
    }

    private fun initListener() {
        mBinding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                setAmount(s.toString())
            }
        })
        mBinding.etTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                setTime(formatTime(s.toString()))
            }
        })
        mBinding.tvSubmit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (TextUtils.isEmpty(mBinding.etAmount.text)) {
                    Toast.makeText(applicationContext, "Amount must be not null", Toast.LENGTH_SHORT).show()
                    return
                }
                if (TextUtils.isEmpty(mBinding.etTime.text)) {
                    Toast.makeText(applicationContext, "Time must be not null", Toast.LENGTH_SHORT).show()
                    return
                }
                val amount = mBinding.etAmount.text.toString().toBigDecimal()
                val time = mBinding.etTime.text.toString().toBigDecimal()
                val intent = Intent(applicationContext, ResultActivity::class.java)
                intent.putExtra("result", "${amount * time}")
                startActivity(intent)
            }
        })
    }

    private fun setAmount(amount: String) {
        mBinding.tvAmount.text = getString(R.string.amount, amount)
    }

    private fun setTime(time: String) {
        mBinding.tvTime.text = getString(R.string.time, time)
    }

    private fun formatTime(time: String): String {
        if (TextUtils.isEmpty(time)) {
            return ""
        }
        val timeValue = Integer.parseInt(time)
        return when {
            timeValue < 0 -> {
                "0s"
            }
            timeValue < 60 -> {
                "${timeValue}s"
            }
            timeValue < 3600 -> {
                "${timeValue / 60}m${timeValue % 60}s"
            }
            else -> {
                val i = timeValue % 3600
                "${timeValue / 3600}h${i / 60}m${i % 60}s"
            }
        }
    }
}
