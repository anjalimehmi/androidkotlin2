package com.o7.androidkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.o7.androidkotlin.databinding.ItemlayoutBinding

class CategoriesListAdapter(var context: Context, var arrayList: ArrayList<CategoriesListModel>, var clickInterface: onClick):
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemlayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding= ItemlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {holder.apply {
        binding.textclasss.setText(arrayList[position].categoryName)

        binding.img.setOnClickListener{
            clickInterface.delete(position)
        }
        binding.btnuppdate.setOnClickListener{
            clickInterface.update(position)
        }
    }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
    interface onClick {
        fun delete(position: Int)
        fun update(position: Int)
    }


}