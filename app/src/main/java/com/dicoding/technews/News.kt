package com.dicoding.technews

data class News(
    var title: String? = null,
    var thumbnail: Int? = null,
    var headline: String? = null,
    var content: String? = null,
    var author: String? = null,
    var published_time: String? = null
)
