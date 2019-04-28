package com.sevenpeakssoftware.khinthirisoe.ui.article.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.khinthirisoe.R
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Content
import com.sevenpeakssoftware.khinthirisoe.ui.base.inflate

class ArticleAdapter(
    private var contentLists: MutableList<Content>?
) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(parent.inflate(R.layout.item_article))
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

    fun setContent(content: MutableList<Content>) {
        this.contentLists = content
        notifyDataSetChanged()
    }
}