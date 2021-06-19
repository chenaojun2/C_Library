package org.chen.cibrary.util

import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap

object ChDataBus {


    private val eventMap = ConcurrentHashMap<String, StickyLiveData<*>>()

    fun <T> with(eventName : String) : StickyLiveData<T> {
        var liveData = eventMap[eventName]
        if(liveData == null) {
            liveData = StickyLiveData<T>(eventName)
            eventMap[eventName] = liveData
        }
        return liveData as StickyLiveData<T>
    }

    class StickyLiveData<T>(private val eventName: String) : LiveData<T>() {

        internal var mStickyData: T? = null
        internal var mVersion = 0

        fun setStickyData(stickyData: T) {
            mStickyData = stickyData
            setValue(stickyData)
        }

        fun postStickyData(stickyData: T) {
            mStickyData = stickyData
            postValue(stickyData)
        }

        override fun setValue(value: T) {
            mVersion++
            super.setValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            observerSticky(owner, false, observer)
        }

        private fun observerSticky(
            owner: LifecycleOwner,
            sticky: Boolean,
            observer: Observer<in T>
        ) {
            owner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    eventMap.remove(eventName)
                }
            })
            super.observe(owner, StickyObserver(this, sticky, observer))
        }
    }

    class StickyObserver<T>(
        val stickyLiveData: StickyLiveData<T>,
        val sticky: Boolean,
        val observer: Observer<in T>
    ) : Observer<T> {

        //lastVersion 和livedata的version 对齐的原因，就是为控制黏性事件的分发。
        //sticky 不等于true , 只能接收到注册之后发送的消息，如果要接收黏性事件，则sticky需要传递为true
        private var lastVersion = stickyLiveData.mVersion

        override fun onChanged(t: T) {

            if(lastVersion >= stickyLiveData.mVersion) {
                if(sticky && stickyLiveData.mStickyData != null) {
                    observer.onChanged(stickyLiveData.mStickyData)
                }
            }

            lastVersion = stickyLiveData.mVersion
            observer.onChanged(t)

        }

    }


}