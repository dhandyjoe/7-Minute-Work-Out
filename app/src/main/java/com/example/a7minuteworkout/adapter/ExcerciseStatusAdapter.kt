package com.example.a7minuteworkout.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.model.ExcerciseModel
import kotlinx.android.synthetic.main.item_excercise_status.view.*

class ExcerciseStatusAdapter(val context: Context, val items: ArrayList<ExcerciseModel>) : RecyclerView.Adapter<ExcerciseStatusAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_excercise_status,parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // penting
        val item: ExcerciseModel = items[position]
        holder.tvItem.text = item.getId() .toString()


        // optional
        if(item.getIsSelected()) {
            holder.tvItem.background = ContextCompat.getDrawable(context,
                R.drawable.item_recycleview
            )
            holder.tvItem.setTextColor(Color.parseColor("#000000"))
        } else if(item.getIsCompleted()) {
            holder.tvItem.background = ContextCompat.getDrawable(context,
                R.drawable.item_recycleview_complete
            )
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}