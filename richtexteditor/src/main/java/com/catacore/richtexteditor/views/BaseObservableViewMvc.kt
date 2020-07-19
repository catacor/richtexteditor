package com.catacore.richtexteditor.views

import java.util.*

abstract class BaseObservableViewMvc<ListenerType> : BaseViewMvc(),
    ObservableViewMvc<ListenerType> {

    private val mListeners = mutableSetOf<ListenerType>()

    final override fun registerListener(listenerType: ListenerType) {
        mListeners.add(listenerType)
    }

    final override fun unregisterListener(listenerType: ListenerType) {
        mListeners.remove(listenerType)
    }

    protected fun getListeners() : Set<ListenerType> {
        return Collections.unmodifiableSet(mListeners)
    }
}