package com.mdocevski.languagelearningnotes

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

val Fragment.application: LearningApplication
        get() = this.activity.application as LearningApplication

val AppCompatActivity.application: LearningApplication
    get() = this.application as LearningApplication