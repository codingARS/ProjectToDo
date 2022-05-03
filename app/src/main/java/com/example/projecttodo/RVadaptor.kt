package com.example.projecttodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class RVadaptor(options: FirestoreRecyclerOptions<Item>) : FirestoreRecyclerAdapter<Item,RVadaptor.RVViewHolder>(
    options
)  {

    class RVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemText: TextView = itemView.findViewById(R.id.itemText)
    }

    //delete an item
    fun deleteItem(position: Int){
        snapshots.getSnapshot(position).reference.delete()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        return  RVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false))
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int, model: Item) {
        holder.itemText.text = model.text
    }

}