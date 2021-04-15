package org.chen.cibrary.restful

/**
 * 代理CallFactory创建出来的call对象，从而实现拦截的派发动作
 * */
class Scheduler(
    private val callFactory: ChCall.Factory,
    private val interceptors: MutableList<ChInterceptor>
) {

    fun newCall(request: ChRequest): ChCall<*> {
        val newCall: ChCall<*> = callFactory.newCall(request)
        return ProxyCall(newCall, request)
    }

    internal inner class ProxyCall<T>(val delegate: ChCall<T>, val request: ChRequest) : ChCall<T> {

        override fun execute(): ChResponse<T> {
            dispatchInterceptor(request, null)

            val response = delegate.execute()

            dispatchInterceptor(request, response)

            return response
        }

        override fun enqueue(callback: ChCallBack<T>) {

            dispatchInterceptor(request, null)

            delegate.enqueue(object : ChCallBack<T> {

                override fun onSuccess(response: ChResponse<T>) {
                    dispatchInterceptor(request,response)

                    if(callback != null) callback.onSuccess(response)
                }

                override fun onFailed(throwable: Throwable) {
                    if(callback != null) callback.onFailed(throwable)
                }

            })

        }

        private fun dispatchInterceptor(request: ChRequest, response: ChResponse<T>?) {
            InterceptorChain(request, response).dispatch()
        }

        internal inner class InterceptorChain(
            val request: ChRequest,
            val response: ChResponse<T>?
        ) : ChInterceptor.Chain {

            var callIndex: Int = 0

            override val isRequestPeriod: Boolean
                get() = response == null

            override fun request(): ChRequest {
                return request
            }

            override fun response(): ChResponse<*>? {
                return response
            }

            fun dispatch() {
                val interceptor = interceptors[callIndex]
                val intercept = interceptor.intercept(this)
                callIndex++
                if (!intercept && callIndex < interceptors.size) {
                    dispatch()
                }
            }

        }

    }
}