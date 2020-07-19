package com.catacore.richtexteditor.interfaces;

import com.catacore.richtexteditor.lib.ActionType;

public interface OnActionPerformListener {
    void onActionPerform(ActionType type, Object... values);
}
