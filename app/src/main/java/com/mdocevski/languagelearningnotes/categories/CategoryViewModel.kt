package com.mdocevski.languagelearningnotes.categories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mdocevski.languagelearningnotes.repository.CategoryItem
import com.mdocevski.languagelearningnotes.repository.Database
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async

class CategoryViewModel : ViewModel() {
    var isInit = false
    lateinit var database: Database
    lateinit var items: LiveData<List<CategoryItem>>
    lateinit var category: String

    suspend fun init(database: Database, category: String) {
        if (isInit) return
        this.database = database
        this.category = category
        items = async(CommonPool) { database.categoryDao().getAllCategoryItems(category) }.await()
        isInit = true
    }

    suspend fun insertItem(foreignLanguageNameValue: String, motherLanguageNameValue: String, motherLanguageNote: String) {
        async(CommonPool) {
            database.categoryDao().insertCategoryItem(CategoryItem(
                    motherLanguageNotes = motherLanguageNote,
                    motherLanguageValue = motherLanguageNameValue,
                    foreignLanguageValue = foreignLanguageNameValue,
                    category = category
            ))
        }.await()
    }
}