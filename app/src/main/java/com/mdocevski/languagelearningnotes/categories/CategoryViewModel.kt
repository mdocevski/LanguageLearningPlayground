package com.mdocevski.languagelearningnotes.categories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mdocevski.languagelearningnotes.repository.CategoryItem
import com.mdocevski.languagelearningnotes.repository.Database

class CategoryViewModel: ViewModel() {
    lateinit var database: Database
    lateinit var items: LiveData<CategoryItem>
    lateinit var category: String

    fun init(database: Database, category: String){
        this.database = database
        this.category = category
        items = database.categoryDao().getAllCategoryItems(category)
    }
}