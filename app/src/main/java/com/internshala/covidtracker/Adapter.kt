package com.internshala.covidtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val list:ArrayList<Modal>,val context: Context):RecyclerView.Adapter<Adapter.CaseViewHolder>() {


    inner class CaseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      val activeCases=itemView.findViewById<TextView>(R.id.activeCases)
        val recoveredCases=itemView.findViewById<TextView>(R.id.recoveredCases)
        val diseased=itemView.findViewById<TextView>(R.id.diseased)

        val confirmedCases=itemView.findViewById<TextView>(R.id.confirmedCases)

        val deltaConfirmed=itemView.findViewById<TextView>(R.id.deltaConfirmed)

        val deltaRecovered=itemView.findViewById<TextView>(R.id.deltaRecovered)

        val deltaDeseased=itemView.findViewById<TextView>(R.id.deltaDeseased)
        val districtName=itemView.findViewById<TextView>(R.id.districtName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return CaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {
        val currentList=list[position]
        holder.activeCases.text="Active : "+currentList.active
        holder.districtName.text=currentList.districtName
        holder.confirmedCases.text="Confirmed : "+currentList.confirmed
        holder.recoveredCases.text="Recovered : "+currentList.recovered
        holder.diseased.text="Deseased : "+currentList.deseased
        holder.deltaConfirmed.text="Confirmed : "+currentList.deltaConfirmed
        holder.deltaDeseased.text="Deseased : "+currentList.deltaDeseased
        holder.deltaRecovered.text="Recovered : "+currentList.deltaRecovered
    }

    override fun getItemCount(): Int {
        return list.size
    }
}