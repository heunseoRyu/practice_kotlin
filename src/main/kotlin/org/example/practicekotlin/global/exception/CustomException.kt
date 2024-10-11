package org.example.practicekotlin.global.exception

import org.example.practicekotlin.global.exception.CustomErrorCode

class CustomException (

    val customErrorCode: CustomErrorCode

) : RuntimeException ()
