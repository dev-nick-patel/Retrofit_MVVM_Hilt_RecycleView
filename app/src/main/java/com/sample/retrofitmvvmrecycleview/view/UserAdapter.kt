package com.sample.retrofitmvvmrecycleview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sample.retrofitmvvmrecycleview.databinding.RawUserListBinding
import com.sample.retrofitmvvmrecycleview.model.User

class UserAdapter: RecyclerView.Adapter<UserAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: RawUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    private val diffCallBack = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var userList: List<User>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RawUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser =userList[position]
        holder.binding.apply {
            profileImage.load("https://dev.codingambitions.com/android/tutorials/images/${currentUser.profile_picture}")
            usernameTV.setText("${currentUser.first_name} ${currentUser.last_name}")
            mobileTV.setText(currentUser.email)
        }
    }

    override fun getItemCount() = userList.size
}