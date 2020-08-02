package com.gid.gidassistant.view.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Dialog {

    public static final class Builder {
        private android.app.Dialog dialog;
        private Context context = null;
        private Integer themeResId = null;
        private Boolean cancelable = null;
        private DialogInterface.OnCancelListener cancelListener = null;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder(@NonNull Context context, int themeResId) {
            this(context);
            this.themeResId = themeResId;
        }

        protected Builder(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
            this(context);
            this.cancelable = cancelable;
            this.cancelListener = cancelListener;
        }

        public android.app.Dialog getServerNotRespondedDialog() {
            dialog = new ServerNotRespondedDialog(context);
            return dialog;
        }
    }
}
