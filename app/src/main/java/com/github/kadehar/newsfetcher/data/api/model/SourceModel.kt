package com.github.kadehar.newsfetcher.data.api.model

import com.google.gson.annotations.SerializedName

data class SourceModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

/*
"source": {
    "id": "engadget",
    "name": "Engadget"
}*/
