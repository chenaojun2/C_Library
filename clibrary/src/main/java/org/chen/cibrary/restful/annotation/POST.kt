package org.chen.cibrary.restful.annotation


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class POST(val value: String, val fromPost : Boolean = true)