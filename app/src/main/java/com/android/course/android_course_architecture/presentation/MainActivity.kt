package com.android.course.android_course_architecture.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.presentation.adapters.MainRecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSearch: Button
    private lateinit var searchField: TextView
    private lateinit var progressBar: ProgressBar

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
        val adapter = MainRecyclerViewAdapter()
        recyclerView.adapter = adapter

        buttonSearch.setOnClickListener {
            scope.launch { viewModel.getAllRecipes(searchField.text.toString()) { errorToast() } }

            viewModel.adapterData.observe(this@MainActivity) {
                adapter.setData(it)
            }
        }

        viewModel.progressBarState.observe(this) {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun errorToast() {
        Toast.makeText(applicationContext, "Something wrong. Try again later", Toast.LENGTH_LONG)
            .show()
    }
}