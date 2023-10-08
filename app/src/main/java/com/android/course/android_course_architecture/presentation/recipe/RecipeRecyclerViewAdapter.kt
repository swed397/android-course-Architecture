package com.android.course.android_course_architecture.presentation.recipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.domain.model.RecipeStep

class RecipeRecyclerViewAdapter :
    RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeRecyclerViewHolder>() {

    var recipesList: List<RecipeStep> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return RecipeRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipeRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<RecipeStep>) {
        recipesList = newData
        notifyDataSetChanged()
    }

    inner class RecipeRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView by lazy { itemView.findViewById(R.id.recipe_recycler_text_view) }
        private val imageView: ImageView by lazy { itemView.findViewById(R.id.recipe_recycler_image_view) }

        fun bind(position: Int) {
            textView.apply {
                text = recipesList[position].text
            }
            imageView.apply {
                setImageBitmap(recipesList[position].image)
            }
        }

    }
}