package com.catacore.richtexteditor.popup.views

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.PopupWindow.OnDismissListener
import com.catacore.richtexteditor.R


class SavePopupViewMvcImpl(private val activity: Activity) : PopupWindow(activity), SavePopupViewMvc
{
    private var mListeners : ArrayList<SavePopupViewMvc.Listener> = ArrayList()

    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private var parentView: View? = null

    init {

        val inflator = activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        contentView = inflator.inflate(R.layout.save_yes_no_popup_layout, null, false)
        height = LinearLayout.LayoutParams.MATCH_PARENT
        width = LinearLayout.LayoutParams.MATCH_PARENT

        isFocusable = true
        setBackgroundDrawable(activity.getDrawable(R.drawable.popup_background))

        setOnDismissListener {
            OnDismissListener {
                for(listener in mListeners)
                    listener.onDismissed()
            }
        }

        parentView = activity.findViewById<View>(android.R.id.content)

        initViewItems()
    }

    private fun initViewItems() {
        yesButton = contentView.findViewById(R.id.pmma_popup_yes)
        noButton = contentView.findViewById(R.id.pmma_popup_no)

        yesButton.setOnClickListener{
            for(listener in mListeners)
                listener.onYesClicked()
        }

        noButton.setOnClickListener{
            for(listener in mListeners)
                listener.onNoClicked()
        }
    }



    override fun show(){
        if (!isShowing && parentView!!.windowToken != null) {
            showAtLocation(parentView, Gravity.CENTER,0,0)
        }

    }

    override fun registerListener(listenerType: SavePopupViewMvc.Listener) {
        mListeners.add(listenerType)
    }

    override fun unregisterListener(listenerType: SavePopupViewMvc.Listener) {
        mListeners.remove(listenerType)
    }

    override fun getRootView(): View {
        return contentView
    }

}