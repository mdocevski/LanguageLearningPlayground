package com.mdocevski.languagelearningnotes.repository

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "category_item")
data class CategoryItem(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val foreignLanguageValue: String,
        val motherLanguageValue: String,
        val motherLanguageNotes: String,
        val category: String)