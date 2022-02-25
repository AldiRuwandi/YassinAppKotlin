package com.example.yassinappkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class AdapterYassin(private val modelBacaan: MutableList<ModelYassin>): RecyclerView.Adapter<AdapterYassin.ListViewHolder>(), Filterable {

    lateinit var modelBacaanListFull: List<ModelYassin>

    override fun getFilter(): Filter {
        return modelBacaanFilter
    }

    private val modelBacaanFilter: Filter = object: Filter(){
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelYassin> = ArrayList()
            if (constraint == null || constraint.length == 0){
                filteredList.addAll(modelBacaanListFull)
            }else {
                val filteredPattern = constraint.toString().toLowerCase()
                for (modelDoaYassin in modelBacaanListFull){
                    if (modelDoaYassin.strLatin!!.toLowerCase().contains(filteredPattern)){
                        filteredList.add(modelDoaYassin)
                    }
                }
            }
            val result = FilterResults()
            result.values = filteredList
            return result
        }

        override fun publishResults(constraint: CharSequence, result: FilterResults?) {
            modelBacaan.clear()
            modelBacaan.addAll(result!!.values as List<ModelYassin>)
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterYassin.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterYassin.ListViewHolder, position: Int) {
        val dataModel = modelBacaan[position]
        holder.tvId.text = dataModel.strID
        holder.tvArabic.text = dataModel.strArabic
        holder.tvLatin.text = dataModel.strLatin
        holder.tvTerjemah.text = dataModel.strTranslation
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvId: TextView
        var tvArabic: TextView
        var tvLatin: TextView
        var tvTerjemah: TextView

        init {
            tvId = itemView.tvId
            tvArabic = itemView.tvArabic
            tvLatin = itemView.tvLatin
            tvTerjemah = itemView.tvTerjemahan
        }
    }

    init {
        modelBacaanListFull = ArrayList(modelBacaan)
    }
}