package org.chen.cibrary.restful

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.ConcurrentHashMap

class ChRestful constructor(val baseUrl: String, val callFactory: ChCall.Factory) {

    private var interceptors: MutableList<ChInterceptor> = mutableListOf()
    private var methodService: ConcurrentHashMap<Method, MethodParser> = ConcurrentHashMap()
    private var scheduler: Scheduler

    init {
        scheduler = Scheduler(callFactory, interceptors)
    }

    fun addInterceptor(interceptor: ChInterceptor) {
        interceptors.add(interceptor)
    }

    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf<Class<*>>(service), object : InvocationHandler {
                override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any {
                    var methodParser = methodService.get(method)
                    if (methodParser == null) {
                        methodParser = MethodParser.parse(baseUrl, method)
                        methodService.put(method, methodParser)
                    }

                    val request = methodParser.newRequest(method,args)
                    return scheduler.newCall(request)
                }

            }
        ) as T
    }

}