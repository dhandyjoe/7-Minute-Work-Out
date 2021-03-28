package com.example.a7minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(val context: Context, val listHistory: ArrayList<String>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val llhistoryAdapter = view.ll_history_adapter
        val position = view.idPosition
        val date = view.tv_date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = listHistory.get(position)
        holder.position.text = (position+1).toString()
        holder.date.text = date

        if(position % 2 == 0) {
            holder.llhistoryAdapter.setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
        } else  {
            holder.llhistoryAdapter.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }

}