package com.sevenpeakssoftware.khinthirisoe.ui.article.view

import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sevenpeakssoftware.khinthirisoe.R
import com.sevenpeakssoftware.khinthirisoe.data.db.model.ArticleContent
import com.sevenpeakssoftware.khinthirisoe.ui.GlideApp
import com.sevenpeakssoftware.khinthirisoe.ui.base.BaseViewHolder
import com.sevenpeakssoftware.khinthirisoe.utils.DateUtils
import kotlinx.android.synthetic.main.list_article.view.*

class ArticleViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun onBind(content: ArticleContent) {

        itemView.txt_title.text = content.title

        itemView.txt_ingress.text = content.ingress

        GlideApp.with(itemView.context)
            .load(content.url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemView.img_car)

        itemView.txt_datetime.text = DateUtils.checkDate(content.datetime)

    }
}

