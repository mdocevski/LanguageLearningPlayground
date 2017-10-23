package com.mdocevski.languagelearningnotes.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdocevski.languagelearningnotes.R

import com.mdocevski.languagelearningnotes.categories.CategoryFragment.OnListFragmentInteractionListener
import com.mdocevski.languagelearningnotes.repository.CategoryItem

class CategoryItemsRecyclerViewAdapter(val values: MutableList<CategoryItem>, var listener: OnListFragmentInteractionListener) : RecyclerView.Adapter<CategoryItemsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = values[position]
        holder.bind()

        holder.view.setOnClickListener {
            listener.onListFragmentInteraction(holder.item!!)
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val motherName: TextView = view.findViewById(R.id.motherName)
        private val foreignName: TextView = view.findViewById(R.id.foreignName)
        private val notes: TextView = view.findViewById(R.id.notes)
        var item: CategoryItem? = null

        override fun toString(): String {
            return super.toString() + "motherName: ${motherName.text}, foreignName: ${foreignName.text}, notes: ${notes.text}"
        }

        fun bind() {
            motherName.text = item?.motherLanguageValue
            foreignName.text = item?.foreignLanguageValue
            notes.text = item?.motherLanguageNotes
        }
    }
}
