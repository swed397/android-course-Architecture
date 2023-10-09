package com.android.course.android_course_architecture.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.presentation.recipe.RecipeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSearch: Button
    private lateinit var searchField: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: MainRecyclerViewAdapter

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val viewModel by lazy { ViewModelProvider(this)[MainActivityViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSearch = findViewById(R.id.search_button)
        searchField = findViewById(R.id.search_field)
        progressBar =
            findViewById<ProgressBar?>(R.id.progress_bar).apply { visibility = View.GONE }

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        adapter = MainRecyclerViewAdapter { uri ->
            onClickRecyclerViewItem(uri)
        }
        recyclerView.adapter = adapter

        buttonSearch.setOnClickListener {
            viewModel.getAllRecipes(searchField.text.toString())
        }

        viewModel.state.observe(this, ::render)
    }

    private fun render(state: MainActivityViewState) {
        when (state) {
            is MainActivityViewState.Loading -> progressBar.isVisible = true
            is MainActivityViewState.Content -> {
                progressBar.isGone = true
                adapter.setData(state.items)
            }

            is MainActivityViewState.Error -> errorToast()
        }
    }

    private fun onClickRecyclerViewItem(uri: String) {
        val fragment = RecipeFragment.newInstance(uri)
        supportFragmentManager.beginTransaction()
            .add(R.id.recipe_fragments_container_view_tag, fragment)
            .commit()
    }

    private fun errorToast() {
        Toast.makeText(applicationContext, "Something wrong. Try again later", Toast.LENGTH_LONG)
            .show()
    }

    companion object {
        const val KEY = "KEY"
    }
}