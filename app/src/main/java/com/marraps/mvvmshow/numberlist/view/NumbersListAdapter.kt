package com.marraps.mvvmshow.numberlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marraps.mvvmshow.R

class NumbersListAdapter(private val numberList: List<Int>) :
    RecyclerView.Adapter<NumbersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_number_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = numberList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tv_number_item).apply {
            text = numberList[position].toString()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}