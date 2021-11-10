package com.example.prayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.prayer.DB.Pray
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter2(var activity: MainActivity2, var list: List<Pray>):  RecyclerView.Adapter<RVAdapter2.ItemViewHolder>(){

    val vm by lazy { ViewModelProvider(activity).get(MyVM::class.java) }

    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var data = list[position]
        // var list1=list[position].meanings[0].definitions[0].definition

        //  var l=list[position].byline.person[position]

        holder.itemView.apply {

            textView.text = data.country
            textView2.text=data.city
            textView3.text=data.imsak
            textView4.text=data.fajr
            textView5.text=data.dhuhr
            textView6.text=data.asr

      checkBox.isVisible=false
            // textView2.text = list.toString()



        }
    }

    override fun getItemCount() = list.size

    fun update(list: List<Pray>){
        this.list = list
        notifyDataSetChanged()
    }





}