package com.example.a7minuteworkout

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.dialog_back_pressed.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history)
        val actionBar = supportActionBar
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "History"
        }

        toolbar_history.setNavigationOnClickListener {
            dialogBackPress()
        }

        getAllHistoryCompleted()
    }

    private fun getAllHistoryCompleted() {
        val database = SQLiteOpenDatabase(this, null)
        val allDateList = database.getAllHistory()

        if(allDateList.size > 0){
            ll_dataDateRecord.visibility = View.VISIBLE
            tv_noDataRecord.visibility = View.GONE

            rv_dataDate.layoutManager = LinearLayoutManager(this)
            val adapter = HistoryAdapter(this, allDateList)
            rv_dataDate.adapter = adapter
        } else {
            ll_dataDateRecord.visibility = View.GONE
            tv_noDataRecord.visibility = View.VISIBLE
        }
    }

    private fun dialogBackPress () {
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_back_pressed)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.tvYes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.tvNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}