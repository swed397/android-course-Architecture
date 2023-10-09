package com.android.course.android_course_architecture.presentation.recipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.domain.model.RecipeStep
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class RecipeRecyclerViewAdapter :
    RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeRecyclerViewHolder>() {

    var recipesList: List<RecipeStep> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_recycler_view_item, parent, false)

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
        private val progressBar: ProgressBar by lazy { itemView.findViewById(R.id.recipe_image_view_progress_bar) }

        fun bind(position: Int) {
            textView.text = "${position + 1}) ${recipesList[position].text}"
            Picasso.get().load(recipesList[position].imageUrl)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        imageView.setImageResource(R.drawable.ic_launcher_foreground)
                    }
                })
        }

    }
}