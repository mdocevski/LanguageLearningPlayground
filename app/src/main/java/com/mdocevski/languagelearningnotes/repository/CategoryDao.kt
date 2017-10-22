package com.mdocevski.languagelearningnotes.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface CategoryDao {
    @Insert(onConflict = REPLACE)
    fun insertCategoryItem(item: CategoryItem)

    @Insert(onConflict = REPLACE)
    fun insertCategoryItems(items: List<CategoryItem>)

    @Delete
    fun deleteCategoryItem(item: CategoryItem)

    @Delete
    fun deleteCategoryItems(items: List<CategoryItem>)

    @Query("SELECT * FROM category_item WHERE category = :category")
    fun getAllCategoryItems(category: String): LiveData<CategoryItem>
}