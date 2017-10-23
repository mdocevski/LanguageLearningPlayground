package com.mdocevski.languagelearningnotes.categories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdocevski.languagelearningnotes.R
import com.mdocevski.languagelearningnotes.application
import com.mdocevski.languagelearningnotes.repository.CategoryItem
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CategoryFragment : Fragment() {
    private lateinit var category: String
    private var mListener: OnListFragmentInteractionListener? = null
    private lateinit var categoryViewModel: CategoryViewModel
    private var initJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            category = arguments.getString(ARG_CATEGORY)
        }
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        initJob = launch(UI) { categoryViewModel.init(application.database, category) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.category_item_list, container, false) as RecyclerView
        val adapter = CategoryItemsRecyclerViewAdapter(mutableListOf(), mListener!!)
        view.adapter = adapter
        activity.findViewById<FloatingActionButton>(R.id.action_button).setOnClickListener {
            launch(UI) {
                categoryViewModel.insertItem(
                        "schlafen", "to sleep", "Schoenes Schlaf"
                )
            }
        }
        initJob?.invokeOnCompletion {
            categoryViewModel.items.observe(this, Observer<List<CategoryItem>> {
                adapter.values.clear()
                adapter.values.addAll(it!!)
                adapter.notifyDataSetChanged()
            })
        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: CategoryItem)
    }

    companion object {
        private val ARG_CATEGORY = "category"

        fun newInstance(category: String): CategoryFragment {
            val fragment = CategoryFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }
}
