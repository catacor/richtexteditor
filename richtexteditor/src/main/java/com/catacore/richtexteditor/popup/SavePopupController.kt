package com.catacore.richtexteditor.popup

import android.app.Activity
import com.catacore.richtexteditor.popup.views.SavePopupViewMvc
import com.catacore.richtexteditor.popup.views.SavePopupViewMvcImpl

class SavePopupController (private val mActivity: Activity) : SavePopupViewMvc.Listener {

    private var mListeners: ArrayList<Listener> = ArrayList()

    interface Listener {
        fun onYesClicked()
        fun onNoClicked()
    }

    private val mViewMvc: SavePopupViewMvc

    init {
        mViewMvc = SavePopupViewMvcImpl(mActivity)
    }

    fun show() {
        mViewMvc.registerListener(this)
        mViewMvc.show()
    }


    override fun onYesClicked() {
        for (listener in mListeners)
            listener.onYesClicked()
        mViewMvc.dismiss()
    }

    override fun onNoClicked() {
        for (listener in mListeners)
            listener.onNoClicked()
        mViewMvc.dismiss()
    }

    override fun onDismissed() {
        mViewMvc.unregisterListener(this)
    }


    fun registerListener(listener: Listener) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: Listener) {
        mListeners.add(listener)
    }
}