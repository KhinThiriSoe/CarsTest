package com.sevenpeakssoftware.khinthirisoe.ui.article.model

import com.google.gson.annotations.SerializedName

data class Content_(
    @SerializedName("type")
    val type: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("description")
    val description: String
)
