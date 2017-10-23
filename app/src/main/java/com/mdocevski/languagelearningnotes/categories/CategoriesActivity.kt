package com.mdocevski.languagelearningnotes.categories

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.mdocevski.languagelearningnotes.R
import com.mdocevski.languagelearningnotes.repository.CategoryItem
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.longToast


class CategoriesActivity : AppCompatActivity(), CategoryFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: CategoryItem) {
        longToast(item.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val fragments = resources.getStringArray(R.array.categories)
        tabs.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return CategoryFragment.newInstance(fragments[position])
            }

            override fun getCount(): Int = fragments.size

            override fun getPageTitle(position: Int): CharSequence = fragments[position]
        }
    }
}