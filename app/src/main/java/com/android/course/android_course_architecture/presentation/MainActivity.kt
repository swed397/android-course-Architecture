package com.android.course.android_course_architecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.di.GlobalDI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSearch: Button
    private lateinit var searchField: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSearch = findViewById(R.id.search_button)
        searchField = findViewById(R.id.search_field)
        progressBar = findViewById<ProgressBar?>(R.id.progress_bar).apply { visibility = View.GONE }

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        val adapter = MainRecyclerViewAdapter()
        recyclerView.adapter = adapter

        buttonSearch.setOnClickListener {
            CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
                val items = GlobalDI.recipeRepository.getAllRecipes(searchField.text.toString())
                adapter.setData(items)
            }
        }
    }
}