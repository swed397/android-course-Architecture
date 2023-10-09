package com.android.course.android_course_architecture.presentation.recipe

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.presentation.main.MainActivity.Companion.KEY

class RecipeFragment : Fragment(R.layout.recipe_fragment) {

    private lateinit var recipeName: TextView
    private lateinit var ingredients: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: RecipeRecyclerViewAdapter

    private val viewModel by lazy { ViewModelProvider(this)[RecipeFragmentViewModel::class.java] }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recipeName = view.findViewById(R.id.recipe_name)
        ingredients = view.findViewById(R.id.ingredients_line)
        progressBar = view.findViewById<ProgressBar>(R.id.recipe_progress_bar)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipe_recycler_view)
        adapter = RecipeRecyclerViewAdapter()
        recyclerView.adapter = adapter

        viewModel.state.observe(requireActivity(), ::render)

        viewModel.getRecipeByUrl(
            arguments?.getString(KEY)
                ?: throw IllegalStateException("No required argument: Recipe URI")
        )
    }

    private fun render(state: RecipeFragmentViewState) {
        when (state) {
            is RecipeFragmentViewState.Loading -> progressBar.isVisible = true
            is RecipeFragmentViewState.Content -> {
                progressBar.isGone = true
                recipeName.text = state.items.recipeName
                ingredients.text = state.items.ingredients
                adapter.setData(state.items.recipeSteps)
            }

            is RecipeFragmentViewState.Error -> errorToast()
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