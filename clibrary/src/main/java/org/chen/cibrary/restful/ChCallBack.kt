package org.chen.cibrary.restful

interface ChCallBack<T> {

    fun onSuccess(response: ChResponse<T>)

    fun onFailed(throwable: Throwable)

}