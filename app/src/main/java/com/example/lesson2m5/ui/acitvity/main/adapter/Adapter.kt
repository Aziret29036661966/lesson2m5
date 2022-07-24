package com.example.lesson2m5.ui.acitvity.main.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson2m5.R
import com.example.lesson2m5.databinding.ItemForRvBinding
import com.example.lesson2m5.model.entity.User

interface UserActionListener{
    fun onUserDelete(user: User)
}

class Adapter(private val actionListener: UserActionListener): RecyclerView.Adapter<Adapter.ViewHolder>(), View.OnLongClickListener{

    var users: List<User> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onLongClick(v: View): Boolean {
        val user = v.tag as User
        when (v.id) {
            else -> {
                actionListener.onUserDelete(user)
            }
        }
        return true
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemForRvBinding.inflate(inflater, parent, false)

        binding.root.setOnLongClickListener(this)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding){
            holder.itemView.tag = user
            txtName.text = user.name
            Glide.with(imgProfile)
                .load(user.photo)
                .circleCrop()
                .into(imgProfile)
        }
    }

    override fun getItemCount(): Int = users.size


    inner class ViewHolder(
        val binding: ItemForRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}
