package com.example.getinsulted


import com.squareup.moshi.Json

data class baddata(
    @Json(name = "active")
    var active: String,
    @Json(name = "comment")
    var comment: String,
    @Json(name = "created")
    var created: String,
    @Json(name = "createdby")
    var createdby: String,
    @Json(name = "insult")
    var insult: String,
    @Json(name = "language")
    var language: String,
    @Json(name = "number")
    var number: String,
    @Json(name = "shown")
    var shown: String
)