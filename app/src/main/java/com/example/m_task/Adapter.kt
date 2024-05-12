package com.example.m_task

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view.view.*

class Adapter(private var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val priority = itemView.priority
        val layout = itemView.mylayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]

        // Set background color based on priority
        when (currentItem.priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }

        holder.title.text = currentItem.title
        holder.priority.text = currentItem.priority

        // Set click listener to open UpdateCard activity
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
