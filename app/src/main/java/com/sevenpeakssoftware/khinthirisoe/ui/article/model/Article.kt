package com.sevenpeakssoftware.khinthirisoe.ui.article.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("status") val status: String,
    @SerializedName("content") val content: List<Content>,
    @SerializedName("serverTime") val serverTime: Int
)
