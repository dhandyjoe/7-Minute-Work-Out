package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*


class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        btn_finish.setOnClickListener {
            finish()
        }

        addDateHistory()
    }

    private fun addDateHistory() {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time

        Log.i("DATE : " , "" + dateTime)

        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SQLiteOpenDatabase(this, null)
        dbHandler.addDate(date)
        Log.i("DATE: ", "Added")
    }

}