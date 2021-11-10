package com.example.prayer

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.prayer.DB.Pray
import kotlinx.android.synthetic.main.item_row.view.*


class RVAdapter(private val activity: MainActivity, var list: List<Display>):  RecyclerView.Adapter<RVAdapter.ItemViewHolder>(){

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
            textView4.text=data.fajer
            textView5.text=data.dhuhr
            textView6.text=data.asr

     var checked=true
           checkBox.isChecked = true
            checkBox.setOnCheckedChangeListener { _, _ ->
                if (checked)  {

                    vm.deletePrayerTime(Pray(data.id,data.country,data.city,data.imsak,data.fajer,
                        data.dhuhr, data.asr))


                }else {


                    vm.addPrayerTime(
                        Pray(data.id,data.country,data.city,data.imsak,data.fajer,
                            data.dhuhr, data.asr))
                    checked = !checked
                }
            }
            // textView2.text = list.toString()



        }
    }

    override fun getItemCount() = list.size

    fun update(list: List<Display>){
        this.list = list
        notifyDataSetChanged()
    }





}