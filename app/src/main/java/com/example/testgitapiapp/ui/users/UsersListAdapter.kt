package com.example.testgitapiapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testgitapiapp.databinding.UsersItemBinding
import com.example.testgitapiapp.models.UsersList

class UsersListAdapter(
    private val onClick: (UsersList) -> Unit
) : PagingDataAdapter<UsersList, SourcesViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
        return SourcesViewHolder(
            UsersItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            Glide
                .with(userAvatarImg.context)
                .load(item!!.avatarUrl)
                .into(userAvatarImg)
            loginTxt.text = item.login
            idTxt.text = item.id.toString()
            root.setOnClickListener {
                onClick(item)
            }
        }
    }
}

class SourcesViewHolder(val binding: UsersItemBinding) : RecyclerView.ViewHolder(binding.root)
class DiffUtilCallback : DiffUtil.ItemCallback<UsersList>() {
    override fun areItemsTheSame(oldItem: UsersList, newItem: UsersList): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UsersList, newItem: UsersList): Boolean =
        oldItem == newItem
}