package com.sevenpeakssoftware.khinthirisoe.ui.article.view

import android.os.Build
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sevenpeakssoftware.khinthirisoe.R
import com.sevenpeakssoftware.khinthirisoe.ui.GlideApp
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Content
import com.sevenpeakssoftware.khinthirisoe.ui.base.BaseViewHolder
import com.sevenpeakssoftware.khinthirisoe.utils.DateUtils
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun onBind(content: Content) {

        itemView.txt_title.text = content.title

        itemView.txt_ingress.text = content.ingress

        GlideApp.with(itemView.context)
            .load(content.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemView.img_car)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            itemView.txt_datetime.text = DateUtils.checkDate(content.dateTime)
        }

    }
}

