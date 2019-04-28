package com.sevenpeakssoftware.khinthirisoe.ui.article

import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Article
import com.sevenpeakssoftware.khinthirisoe.ui.base.BasePresenter
import com.sevenpeakssoftware.khinthirisoe.ui.base.BaseView

class ArticleContract {

    interface View : BaseView {

        fun showArticleLists(article: Article)

        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter<View> {

        fun fetchArticleLists()
    }
}