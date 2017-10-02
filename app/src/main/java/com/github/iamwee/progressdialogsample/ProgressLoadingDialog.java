package com.github.iamwee.progressdialogsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class ProgressLoadingDialog extends DialogFragment {

    private static final String TAG = "ProgressLoadingDialog";

    public static ProgressLoadingDialog create() {
        return new ProgressLoadingDialog();
    }

    public static void show(FragmentManager manager) {
        ProgressLoadingDialog dialog = ProgressLoadingDialog.create();

        try {
            dialog.show(manager, TAG);
        } catch (IllegalStateException ignored) {
            manager.beginTransaction()
                    .add(dialog, TAG)
                    .commitAllowingStateLoss();
        }
    }

    public static void dismiss(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag(TAG);
        if (fragment == null) return;

        ProgressLoadingDialog dialog;
        try {
            dialog = (ProgressLoadingDialog) fragment;
        } catch (ClassCastException e) {
            Log.e(TAG, "Cannot dismiss dialog because cannot cast fragment tag " + TAG + " to progress loading dialog.");
            return;
        }

        try {
            dialog.dismiss();
        } catch (IllegalStateException ignored) {
            dialog.dismissAllowingStateLoss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.view_dialog_loading, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }
}
