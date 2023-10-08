package com.android.course.android_course_architecture.presentation.recipe

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.presentation.main.MainActivity.Companion.KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RecipeFragment : Fragment(R.layout.recipe_fragment) {

    private lateinit var recipeName: TextView
    private lateinit var ingredients: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val viewModel by lazy { ViewModelProvider(this)[RecipeFragmentViewModel::class.java] }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recipeName = view.findViewById(R.id.recipe_name)
        ingredients = view.findViewById(R.id.ingredients_line)
        recyclerView = view.findViewById(R.id.recipe_recycler_view)
        progressBar = view.findViewById<ProgressBar>(R.id.recipe_progress_bar)
            .apply { visibility = View.GONE }

        val adapter = RecipeRecyclerViewAdapter().apply {
            scope.launch {
                viewModel.getRecipeByUrl(
                    arguments?.getString(KEY)
                        ?: throw IllegalStateException("No required argument: Recipe URI")
                ) { errorToast() }
            }
        }
        viewModel.recipesSteps.observe(requireActivity()) {
            adapter.setData(it)
        }

        viewModel.progressBarState.observe(requireActivity()) {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun errorToast() {
        Toast.makeText(context, "Something wrong. Try again later", Toast.LENGTH_LONG)
            .show()
    }

    companion object {
        fun newInstance(uri: String): RecipeFragment = RecipeFragment().apply {
            arguments = bundleOf(KEY to uri)
        }
    }
}