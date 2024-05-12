package com.example.m_task

object DataObject {
    private val listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String) {
        listdata.add(CardInfo(title, priority))
    }

    fun getAllData(): List<CardInfo> {
        return listdata.toList() // Return a copy of the list to prevent external modification
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo? {
        return if (pos in 0 until listdata.size) {
            listdata[pos]
        } else {
            null // Return null if index is out of bounds
        }
    }

    fun deleteData(pos:Int){
        if (pos in 0 until listdata.size) {
            listdata.removeAt(pos)
        }
    }

    fun updateData(pos:Int,title:String,priority:String)
    {
        if (pos in 0 until listdata.size) {
            listdata[pos] = CardInfo(title, priority)
        }
    }
}
