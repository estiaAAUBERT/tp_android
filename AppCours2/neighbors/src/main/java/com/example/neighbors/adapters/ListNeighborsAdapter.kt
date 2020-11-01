package com.example.neighbors.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.neighbors.R
import com.example.neighbors.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>, handler:ListNeighborHandler) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {

    private val mNeighbours: List<Neighbor> = items
    private val mhandler:ListNeighborHandler = handler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.neighbor_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        val context = holder.itemView.context

        // Display Neighbour Avatar
        Glide.with(context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(holder.mNeighbourAvatar)
        // Display Neighbour Name
        holder.mNeighbourName.text = neighbour.name
        //Call Delete Function by clicking button
        holder.mDeleteButton.setOnClickListener {
            mhandler.onDeleteNeighbor(neighbour)
        }
    }



    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mNeighbourAvatar: ImageView
        val mNeighbourName: TextView
        val mDeleteButton: ImageButton


        init {
            // Enable click on item
            mNeighbourAvatar = view.findViewById(R.id.item_list_avatar)
            mNeighbourName = view.findViewById(R.id.item_list_name)
            mDeleteButton = view.findViewById(R.id.item_list_delete_button)
        }
    }


}