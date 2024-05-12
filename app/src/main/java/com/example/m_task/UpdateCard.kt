package com.example.m_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.m_task.databinding.ActivityUpdateCardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateCardBinding
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos)?.title // Remove this line as we're fetching data from the database
            val priority = DataObject.getData(pos)?.priority // Remove this line as we're fetching data from the database
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)

            binding.deleteButton.setOnClickListener {
                deleteTask(pos)
            }

            binding.updateButton.setOnClickListener {
                updateTask(pos)
            }
        } else {
            showToast("Invalid position")
            finish()
        }
    }

    private fun deleteTask(pos: Int) {
        GlobalScope.launch {
            try {
//                database.dao().deleteTask(pos + 1)
                DataObject.deleteData(pos)
                showToast("Task deleted successfully")
                myIntent()
            } catch (e: Exception) {
                showToast("Failed to delete task")
            }
        }
    }

    private fun updateTask(pos: Int) {
        val title = binding.createTitle.text.toString().trim() // Trim the input
        val priority = binding.createPriority.text.toString().trim() // Trim the input

        if (title.isNotEmpty() && priority.isNotEmpty()) {
            GlobalScope.launch {
                try {
                    database.dao().updateTask(Entity(pos + 1, title, priority))
                    DataObject.updateData(pos, title, priority)
                    showToast("Task updated successfully")
                    myIntent()
                } catch (e: Exception) {
                    showToast("Failed to update task")
                }
            }
        } else {
            showToast("Title or Priority cannot be empty")
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
