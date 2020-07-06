package com.example.week3.stringweek3.cons

import java.util.regex.Pattern

const val BASE_URL="http://string-api.vinova.sg/api/"
 val  Email_Address:Pattern=Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,126}"+
            "\\@"+
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
            "("+
            "\\."+
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
            ")+")
val Username:Pattern= Pattern.compile("([a-zA-Z0-9_]{4,})")
