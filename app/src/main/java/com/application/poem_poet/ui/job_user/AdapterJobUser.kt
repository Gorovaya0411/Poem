package com.application.poem_poet.ui.job_user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.poem_poet.R
import com.application.poem_poet.databinding.ItemViewJobUserBinding
import com.application.poem_poet.model.PoemAnswer


class AdapterJobUser(private var callback: (PoemAnswer, Int) -> Unit) :
    RecyclerView.Adapter<AdapterJobUser.MyViewHolder>() {
    private var dataTest = mutableListOf<PoemAnswer?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<PoemAnswer?>) {
        this.dataTest = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_job_user,
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int = dataTest.count()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        dataTest[position]?.let { holder.bind(it) }
    }

    class MyViewHolder(itemView: View, private var callback: (PoemAnswer, Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var binding = ItemViewJobUserBinding.bind(itemView)
        private var title: TextView = binding.jobUserTitleTxt
        private var name: TextView = binding.jobUserNameTxt
        private var genre: TextView = binding.jobUserGenreTxt
        private var nameModified = ""
        private var like: TextView = binding.jobUsersNumberLikesTxt

        fun bind(model: PoemAnswer) {
            nameModified = model.namePoet.replace("|", ".", true)
            if (model.namePoet == "") {
                name.text = model.username

            } else {
                name.text = nameModified
            }
            title.text = model.titlePoem

            genre.text = model.genre
            like.text = model.like.toString()

            itemView.setOnClickListener {
                callback.invoke(model, 1)
            }
            itemView.setOnLongClickListener {
                callback.invoke(model, 2)
                return@setOnLongClickListener true
            }
        }
    }
}