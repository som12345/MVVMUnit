package com.example.mvvmunittesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmunittesting.R
import com.example.mvvmunittesting.model.Android
import com.example.mvvmunittesting.model.ApiResponseModel
import kotlinx.android.synthetic.main.custom_layout.view.*

class AndroidListAdapter(
    private val users: ArrayList<Android>
) : RecyclerView.Adapter<AndroidListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(apiResponseModel:  Android) {
            itemView.tv_name.text = apiResponseModel.name
            itemView.tv_api_level.text = apiResponseModel.api
            itemView.tv_version.text = apiResponseModel.ver
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.custom_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: ApiResponseModel) {
        users.addAll(list.android)
    }
}