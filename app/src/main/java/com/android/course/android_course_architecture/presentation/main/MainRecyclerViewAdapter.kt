package com.android.course.android_course_architecture.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.android_course_architecture.R
import com.android.course.android_course_architecture.domain.model.RecipesModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class MainRecyclerViewAdapter(
    private val onClickItem: (uri: String) -> Unit
) : RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    var recipesList: List<RecipesModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return MainRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<RecipesModel>) {
        recipesList = newData
        notifyDataSetChanged()
    }

    inner class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val labelTextView: TextView by lazy { itemView.findViewById(R.id.recycler_item_text_view) }
        private val caloriesTextView: TextView by lazy { itemView.findViewById(R.id.recycler_item_text_view_calories) }
        private val imageView: ImageView by lazy { itemView.findViewById(R.id.recycle_item_image_view) }
        private val progressBar: ProgressBar by lazy { itemView.findViewById(R.id.main_image_view_progress_bar) }

        init {
            itemView.setOnClickListener {
                onClickItem.invoke(
                    recipesList[bindingAdapterPosition].uri
                )
            }
        }

        fun bind(position: Int) {
            labelTextView.text = recipesList[position].label
            caloriesTextView.apply {
                text = "$text ${recipesList[position].calories}"
            }
            Picasso.get().load(recipesList[bindingAdapterPosition].imageUrl)
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