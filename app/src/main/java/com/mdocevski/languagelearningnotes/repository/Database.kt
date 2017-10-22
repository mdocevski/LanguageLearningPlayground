package com.mdocevski.languagelearningnotes.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = arrayOf(CategoryItem::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}