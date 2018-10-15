package com.aranteknoloji.ktroom.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aranteknoloji.ktroom.R
import com.aranteknoloji.ktroom.db.User

class UsersAdapter(var users: List<User> = arrayListOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_users_list, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.nameTextView.text = users.get(position).firstname
        myViewHolder.lastnameView.text = users.get(position).lastname
    }

    inner class MyViewHolder(
            itemView: View,
            val nameTextView: TextView = itemView.findViewById(R.id.name),
            val lastnameView: TextView = itemView.findViewById(R.id.lastname)
    ): RecyclerView.ViewHolder(itemView)
}