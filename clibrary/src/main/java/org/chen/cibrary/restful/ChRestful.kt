package org.chen.cibrary.restful

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
            arrayOf<Class<*>>(service)
        ) { proxy, method, args ->

            var methodParser = methodService.get(method)
            if (methodParser == null) {
                methodParser = MethodParser.parse(baseUrl, method, args)
                methodService.put(method, methodParser)
            }

            val request = methodParser.newRequest()
            scheduler.newCall(request)

        } as T
    }

}