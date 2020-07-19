package com.catacore.richtexteditor.views

interface ObservableViewMvc<ListenerType> : ViewMvc {
    fun registerListener(listenerType: ListenerType)
    fun unregisterListener(listenerType: ListenerType)
}