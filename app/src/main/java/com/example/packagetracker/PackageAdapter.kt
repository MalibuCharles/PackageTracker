package com.example.packagetracker

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_package.view.*

class PackageAdapter(
    private val packages: MutableList<Package>
) : RecyclerView.Adapter<PackageAdapter.PackageViewHolder>(){

    class  PackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackageViewHolder {
        return PackageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_package,
                parent,
                false
            )
        )
    }

    fun addPackage(packae : Package){
        packages.add(packae)
        notifyItemInserted(packages.size -1)
    }

    fun deleteDeliveredPackages(){
        packages.removeAll { packae ->
            packae.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvPackageTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvPackageTitle.paintFlags = tvPackageTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvPackageTitle.paintFlags = tvPackageTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: PackageViewHolder, position: Int) {
        val curPackage = packages[position]
        holder.itemView.apply {
            tvPackageTitle.text = curPackage.title
            cbDone.isChecked = curPackage.isChecked
            toggleStrikeThrough(tvPackageTitle, curPackage.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvPackageTitle, isChecked)
                curPackage.isChecked = !curPackage.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return packages.size
    }
}