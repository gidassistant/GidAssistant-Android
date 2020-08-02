package com.gid.gidassistant.view.dialogs;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ServerNotRespondedDialog extends Dialog {

    public ServerNotRespondedDialog(@NonNull Context context) {
        super(context);
    }

    public ServerNotRespondedDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ServerNotRespondedDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
