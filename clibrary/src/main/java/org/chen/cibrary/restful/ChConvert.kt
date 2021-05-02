package org.chen.cibrary.restful

import java.lang.reflect.Type

interface ChConvert {

    fun <T> convert(rawData: String, dataType: Type): ChResponse<T>

}