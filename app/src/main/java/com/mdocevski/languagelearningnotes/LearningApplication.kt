package com.mdocevski.languagelearningnotes

import android.app.Application
import android.arch.persistence.room.Room
import com.facebook.stetho.Stetho
import com.mdocevski.languagelearningnotes.repository.Database

class LearningApplication : Application() {
    lateinit var database: Database
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, Database::class.java, "learning-database").build()
        Stetho.initializeWithDefaults(this)
    }
}