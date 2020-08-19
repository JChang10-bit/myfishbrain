package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catch_info_card.view.*

class CatchInfoRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: MutableList<CatchInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CatchInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.catch_info_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CatchInfoViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    fun submitList(CatchInfoList: MutableList<CatchInfo>){
        items = CatchInfoList
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CatchInfoViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val catchInfoDate: TextView = itemView.date
        private val catchInfoLocation: TextView = itemView.location
        private val catchInfoSpecies: TextView = itemView.species
        private val catchInfoWeight: TextView = itemView.weight
        private val catchInfoLureType: TextView = itemView.lure_type
        private val catchInfoRodPower: TextView = itemView.rod_power
        private val catchInfoRodLength: TextView = itemView.rod_length
        private val catchInfoReelRatio: TextView = itemView.reel_ratio
        private val catchInfoLineType: TextView = itemView.line_type
        private val catchInfoLinePounds: TextView = itemView.line_pounds
        private val catchInfoWaterConditions: TextView = itemView.water_condition

        fun bind(catchInfoInstance: CatchInfo){
            catchInfoDate.text = catchInfoInstance.date
            catchInfoLocation.text = catchInfoInstance.location
            catchInfoSpecies.text = catchInfoInstance.species
            catchInfoWeight.text = catchInfoInstance.weight
            catchInfoLureType.text = catchInfoInstance.lureType
            catchInfoRodPower.text = catchInfoInstance.rodPower
            catchInfoRodLength.text = catchInfoInstance.rodLength
            catchInfoReelRatio.text = catchInfoInstance.reelRatio
            catchInfoLineType.text = catchInfoInstance.lineType
            catchInfoLinePounds.text = catchInfoInstance.linePounds
            catchInfoWaterConditions.text = catchInfoInstance.waterCondition
        }
    }
}