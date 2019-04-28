package com.sevenpeakssoftware.khinthirisoe.ui.article.model

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("tags")
    var tags: List<Any>?,
    @SerializedName("content")
    var content: List<Content_>?,
    @SerializedName("ingress")
    val ingress: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("created")
    var created: Int?,
    @SerializedName("changed")
    var changed: Int?
)