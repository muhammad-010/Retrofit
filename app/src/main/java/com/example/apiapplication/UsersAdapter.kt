package com.example.apiapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiapplication.databinding.ItemUsersBinding
import com.example.apiapplication.model.data
import com.example.apiapplication.model.users

typealias OnclickUsers = (data) -> Unit
class UsersAdapter (private var listusers : List<data>, private val onclickUsers: OnclickUsers) : RecyclerView.Adapter<UsersAdapter.ItemUsersViewHolder>() {
    inner class ItemUsersViewHolder(private val binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: data) {
            with(binding) {
                txtName.text = "${users.first_name} ${users.last_name}"
                txtEmail.text = "${users.email}"
                Glide.with(imageHero.context).load(users.avatar).into(imageHero)

                itemView.setOnClickListener {
                    onclickUsers(users)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUsersViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemUsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listusers.size
    }

    override fun onBindViewHolder(holder: ItemUsersViewHolder, position: Int) {
        holder.bind(listusers[position])
    }
    fun setData(newList: List<data>) {
        listusers = newList
        notifyDataSetChanged()
    }
}