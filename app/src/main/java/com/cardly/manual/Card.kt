package com.cardly.manual

import java.io.Serializable

data class Card(
    var brand: String = "",
    var expiryMillis: Long = 0L,
    var notes: String = ""
) : Serializable
