package br.com.ebss.desafio_android_eduardo_seguro.model

import br.com.ebss.desafio_android_eduardo_seguro.utils.ConstantsAPI
import br.com.ebss.desafio_android_eduardo_seguro.utils.md5
import java.sql.Timestamp
import java.util.*

class ApiHash (
    val timestamp: String,
    val hash: String
) {
    constructor(timestamp: String) : this (
        timestamp,
        "${timestamp}${ConstantsAPI.PRIVATE_KEY}${ConstantsAPI.PUBLIC_KEY}".md5()
    )
}