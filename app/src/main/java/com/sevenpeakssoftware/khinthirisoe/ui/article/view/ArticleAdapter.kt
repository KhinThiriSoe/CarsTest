package com.sevenpeakssoftware.khinthirisoe.ui.article.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.khinthirisoe.R
import com.sevenpeakssoftware.khinthirisoe.data.db.model.ArticleContent
import com.sevenpeakssoftware.khinthirisoe.ui.base.inflate

class ArticleAdapter(
    private var contentLists: MutableList<ArticleContent>?
) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(parent.inflate(R.layout.list_article))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        holder.onBind(contentLists!![position])
    }

    override fun getItemCount(): Int {

        return if (contentLists != null) {
            contentLists!!.size
        } else {
            0
        }
    }

    fun setContent(content: MutableList<ArticleContent>) {
        this.contentLists = content
        notifyDataSetChanged()
    }
}