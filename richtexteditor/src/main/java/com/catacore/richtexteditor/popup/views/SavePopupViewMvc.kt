package com.catacore.richtexteditor.popup.views

import com.catacore.richtexteditor.views.ObservableViewMvc


interface SavePopupViewMvc : ObservableViewMvc<SavePopupViewMvc.Listener> {
    interface Listener {
        fun onYesClicked()
        fun onNoClicked()
        fun onDismissed()
    }

    fun show()
    fun dismiss()
}