package com.benohayon.tiprankshomeassignment.view.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.benohayon.tiprankshomeassignment.R

class SearchBoxView : LinearLayout {

    interface SearchBoxViewListener {
        fun onSearchClick(searchQuery: String)
        fun onTextChanged()
    }

    private var editText: EditText? = null
    private var searchButton: ImageView? = null
    private var clearButton: ImageView? = null

    private var searchListener: SearchBoxViewListener? = null

    val searchQuery: String?
        get() = editText?.text?.toString()

    constructor(context: Context) : super(context) {
        initialize(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initialize(context)
    }

    private fun initialize(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_search_box, this)
        initUI(view)
    }

    private fun initUI(view: View?) {
        editText = view?.findViewById(R.id.searchBoxViewEditText)
        searchButton = view?.findViewById(R.id.searchBoxViewSearchButton)
        clearButton = view?.findViewById(R.id.searchBoxViewClearButton)

        clearButton?.setOnClickListener {
            editText?.setText("")
            clearButton?.visibility = View.GONE
        }

        editText?.addTextChangedListener(afterTextChanged = {
            if (it?.toString()?.isEmpty() == true)
                clearButton?.visibility = View.GONE
            else
                clearButton?.visibility = View.VISIBLE
        })

        editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && editText?.text?.toString()?.isNotEmpty() == true) {
                performSearch()
            }

            true
        }

        editText?.addTextChangedListener {
            searchListener?.onTextChanged()
        }

        searchButton?.setOnClickListener {
            if (editText?.text?.toString()?.isNotEmpty() == true)
                performSearch()
        }
    }

    private fun performSearch() {
        hideSoftKeyboard()
        searchListener?.onSearchClick(editText?.text?.toString()!!)
    }

    fun setOnSearchListener(searchListener: SearchBoxViewListener) {
        this.searchListener = searchListener
    }

    private fun hideSoftKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText?.windowToken, 0)
    }
}