package org.chen.cibrary.restful

interface ChInterceptor {

    fun intercept(chan: Chain): Boolean


    /**
     *Chain 对象会在派发拦截器的时候创建
     * */
    interface Chain {

        val isRequestPeriod:Boolean get() = false

        fun request(): ChRequest

        /**
         * response对象 在网络发起之前， 是为空的
         * */
        fun response(): ChResponse<*>
    }

}