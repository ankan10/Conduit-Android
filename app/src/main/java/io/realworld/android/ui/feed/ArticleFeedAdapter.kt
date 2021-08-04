package io.realworld.android.ui.feed


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.realworld.android.R
import io.realworld.android.databinding.ListItemArticleBinding
import io.realworld.api.models.entities.Article



class ArticleFeedAdapter(val onArticleClicked: (slug: String) -> Unit) :
    ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
        object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    ) {
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    lateinit var view: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        view=parent
        return ArticleViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.list_item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body

            dateTextView.text = article.createdAt.substring(0, 10)+" "+article.createdAt.substring(11, 19)
            val imageUrl: String?= article.author.image


            root.setOnClickListener { onArticleClicked(article.slug) }

        }

    }


}