package com.lsimanenka.coderefactor4.presentation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lsimanenka.coderefactor4.R

class DetailActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
        val userName = intent.getStringExtra(MainActivity.EXTRA_USER_NAME)
        if (userName != null) {
            tvDetailName.text = userName
        } else {
            tvDetailName.text = "Пользователь не найден"
        }
    }
}