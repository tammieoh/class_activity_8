package com.example.kotlinexample1

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.view.View as View

class VillagerAdapter (private val dataset:List<Villager>) : RecyclerView.Adapter<VillagerAdapter.ItemViewHolder>() {
//    private var villagers = ArrayList<Villager>()
    private var selectedVillager = -1
    private var selectedVillagers = ArrayList<Villager>()


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textViewName: TextView = view.findViewById(R.id.textView_name)
        val textViewBirthday: TextView = view.findViewById(R.id.textView_birthday)
        val textViewPhrase: TextView = view.findViewById(R.id.textView_phrase)
        val imageViewVillager: ImageView = view.findViewById(R.id.imageView_villager)

        override fun onClick(v: View?) {
            Log.d("view_clicked", v.toString())
            var selected = adapterPosition
            Log.d("position", selected.toString())
            var selectedV = dataset[selected]
            Log.d("clicked", selectedV.name)

            if(selectedVillagers.contains(selectedV)){
                selectedVillagers.remove(selectedV);
            }
            else {
                selectedVillagers.add(selectedV)
            }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_villager, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var villager:Villager = dataset[position]
        holder.textViewName.text = villager.name
        holder.textViewBirthday.text = villager.birthday
        holder.textViewPhrase.text = villager.phrase
        // load in the image
        Picasso.get().load(villager.houseURL).into(holder.imageViewVillager)

        holder.itemView.setOnClickListener {
            Log.d("Holder", "Click")
//            var selected = adapterPosition
//            Log.d("position", selected.toString())
            var selectedV = dataset[position]
//            Log.d("clicked", selectedV.name)

            if(selectedVillagers.contains(selectedV)){
                selectedVillagers.remove(selectedV);
            }
            else {
                selectedVillagers.add(selectedV)
            }
            notifyDataSetChanged()}

        if(selectedVillagers.contains(villager)) {
            Picasso.get().load(villager.villageURL).into(holder.imageViewVillager)
        }
        else {
            Picasso.get().load(villager.houseURL).into(holder.imageViewVillager)
        }
    }
}
