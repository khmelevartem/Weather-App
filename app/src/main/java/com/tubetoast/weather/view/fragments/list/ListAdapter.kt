package com.tubetoast.weather.view.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tubetoast.weather.R
import com.tubetoast.weather.databinding.ListItemBinding
import com.tubetoast.weather.model.entities.City

class ListAdapter(
    private val onItemClick: (City?) -> Unit,
) : RecyclerView.Adapter<ViewHolder>(){

    private var data: List<City>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = data?.get(position)
        holder.bind(city)

        holder.itemView.setOnClickListener{
            onItemClick(city)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun setData(data: List<City>) {
        this.data = data
        notifyDataSetChanged()
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(city : City?) {
        val binding = ListItemBinding.bind(itemView)
        binding.cityName.text = city?.name
    }
}