package org.chen.cibrary.restful

import java.io.IOException

interface ChCall<T> {

    @Throws(IOException::class)
    fun execute():ChResponse<T>

    fun enqueue(callback: ChCall<T>)

    interface Factory{
        fun newCall(request:ChRequest) :ChCall<*>
    }

}