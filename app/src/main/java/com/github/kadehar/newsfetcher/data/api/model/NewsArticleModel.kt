package com.github.kadehar.newsfetcher.data.api.model

import com.google.gson.annotations.SerializedName

data class NewsArticleModel(
    @SerializedName("source")
    val source: NewsSourceModel,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)

/*{
"source": {
"id": "engadget",
"name": "Engadget"
},
"author": "Jon Fingas",
"title": "AMC theaters will accept cryptocurrencies beyond Bitcoin",
"description": "You won't have to stick to Bitcoin if you're determined to pay for your movie ticket with cryptocurrency. AMC chief Adam Aron has revealed his theater chain will also accept Ethereum, Litecoin and Bitcoin Cash when crypto payments are available. He didn't hav…",
"url": "https://www.engadget.com/amc-theaters-accept-ethereum-litecoin-bitcoin-cash-132642183.html",
"urlToImage": "https://s.yimg.com/os/creatr-uploaded-images/2021-09/4a01cb80-16eb-11ec-abfe-c7b840dd48ca",
"publishedAt": "2021-09-16T13:26:42Z",
"content": "You won't have to stick to Bitcoin if you're determined to pay for your movie ticket with cryptocurrency. AMC chief Adam Aron has revealed his theater chain will also accept Ethereum, Litecoin and Bi… [+1198 chars]"
}*/
