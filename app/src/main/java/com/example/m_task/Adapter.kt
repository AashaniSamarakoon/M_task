// CardInfo.kt
package com.example.m_task

// Adapter.kt
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.m_task.databinding.ViewBinding

class Adapter(private var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cardInfo: CardInfo) {
            binding.cardInfo = cardInfo
            binding.executePendingBindings()

            // Set background color based on priority
            when (cardInfo.priority.toLowerCase()) {
                "high" -> binding.mylayout.setBackgroundColor(Color.parseColor("#F05454"))
                "medium" -> binding.mylayout.setBackgroundColor(Color.parseColor("#EDC988"))
                else -> binding.mylayout.setBackgroundColor(Color.parseColor("#00917C"))
            }

            // Set click listener to open UpdateCard activity
            binding.root.setOnClickListener {
                val intent = Intent(it.context, UpdateCard::class.java)
                intent.putExtra("id", adapterPosition)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding = DataBindingUtil.inflate(inflater, R.layout.view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
