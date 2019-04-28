package com.sevenpeakssoftware.khinthirisoe.data.network

import com.sevenpeakssoftware.khinthirisoe.ui.article.model.Article
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(ApiUrl.GET_ARTICLE_LIST)
    fun getArticleLists(): Single<Article>
}