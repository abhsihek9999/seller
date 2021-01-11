package com.abhishek.seller.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.abhishek.seller.R;
import com.abhishek.seller.core.application.HKApp;
import com.abhishek.seller.core.common.CommonCallBackListner;
import com.abhishek.seller.core.common.Status;
import com.abhishek.seller.utils.alert_dialog.CommonErrorDialog;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class ViewUtils {
    private static CommonErrorDialog commonErrorDialog;
    private static CommonCallBackListner commonCallBackListner;

    public static TextView createLink(TextView targetTextView, String completeString, String partToClick, ClickableSpan clickableAction) {

        SpannableString spannableString = new SpannableString(completeString);

        // make sure the String is exist, if it doesn't exist
        // it will throw IndexOutOfBoundException
        int startPosition = completeString.indexOf(partToClick);
        int endPosition = completeString.lastIndexOf(partToClick) + partToClick.length();

        spannableString.setSpan(clickableAction, startPosition, endPosition,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        targetTextView.setText(spannableString);
        targetTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return targetTextView;
    }

    public static void hideKeyboard(Context context) {
        if (context == null) {
            return;
        }
        View view = ((AppCompatActivity) context).getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showSnackBar(View rootLayout, String message, String tittle) {
        try {
            openErrorDialog(rootLayout.getContext(), message, tittle);
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        }

    }

    private static void openErrorDialog(Context context, String msg, String tittle) {
        if (commonErrorDialog != null && commonErrorDialog.isShowing()) {
            commonErrorDialog.dismiss();
            commonErrorDialog = null;
        }

        commonErrorDialog = new CommonErrorDialog(context, tittle, msg);
        Objects.requireNonNull(commonErrorDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        commonErrorDialog.setSingleBtnTxt(HKApp.getAppContext().getResources().getString(R.string.ok));
        commonErrorDialog.setCancelable(false);
        commonErrorDialog.show();

        commonErrorDialog.setOkCallback(new CommonErrorDialog.OkCallcack() {
            @Override
            public void clickOkCallback() {
                if (commonCallBackListner != null) {
                    commonCallBackListner.commonEventListner(AppUtil.getCommonClickModel(-1, Status.ERROR_DIALOG_OK_CALLBACK, ""));
                }
                /*After send the callBack it becomes null*/
                commonCallBackListner = null;

                commonErrorDialog.dismiss();
            }
        });
    }
}
