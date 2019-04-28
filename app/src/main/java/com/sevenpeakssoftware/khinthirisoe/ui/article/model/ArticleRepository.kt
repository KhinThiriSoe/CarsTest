package com.sevenpeakssoftware.khinthirisoe.ui.article.model

import com.sevenpeakssoftware.khinthirisoe.data.network.ApiService
import javax.inject.Inject

class ArticleRepository @Inject
constructor(private val apiService: ApiService) {

    fun getArticleLists() = apiService.getArticleLists()
}