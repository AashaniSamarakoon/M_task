package com.example.m_task

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.m_task.databinding.ViewBinding

class Adapter(private val data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val context = it.context
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, UpdateCard::class.java)
                    intent.putExtra("id", position)
                    context.startActivity(intent)
                }
            }
        }

        fun bind(cardInfo: CardInfo) {
            binding.cardInfo = cardInfo

            // Set background color based on priority
            when (cardInfo.priority.toLowerCase()) {
                "high" -> binding.mylayout.setBackgroundColor(Color.parseColor("#F05454"))
                "medium" -> binding.mylayout.setBackgroundColor(Color.parseColor("#EDC988"))
                else -> binding.mylayout.setBackgroundColor(Color.parseColor("#00917C"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding = DataBindingUtil.inflate(inflater, R.layout.view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
