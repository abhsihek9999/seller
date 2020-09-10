package com.hk.seller.utils;

import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;


import com.hk.seller.R;
import com.hk.seller.core.application.HKApp;

import java.util.List;

public class TextUtil {

    public static void setBoldText(TextView pTextView, String pStringMsg, int pStartPoint, int pEndPoint) {
        SpannableString spanString = new SpannableString(pStringMsg);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), pStartPoint, pEndPoint, 0);
        pTextView.setText(spanString);
    }

    public static boolean isEmpty(String text) {
        if (text != null && !text.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public static String getCamelCase(String text) {

        String upper = text.toUpperCase();
        return upper.substring(0, 1) + upper.substring(1).toLowerCase();

    }

    public static int getStringCount(String text) {
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ')
                count++;
        }
        return count;
    }


    public static CharSequence noTrailingwhiteLines(CharSequence text) {

        while (text.charAt(text.length() - 1) == '\n') {
            text = text.subSequence(0, text.length() - 1);
        }
        return text;
    }


    public static String getAedReplaceString(String realString) {
        String aedStr = HKApp.getAppContext().getString(R.string.app_name) + " ";
        String replaceString = realString.replace(aedStr, "");
        String commareplaceString = replaceString.replace(",", "");
        return commareplaceString;

    }


    public static boolean checkListIsEmpty(List data) {
        if (data != null && !data.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public static void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }


    public static String removeAllSpace(String text) {
        return text.replaceAll("\\s+", "");
    }


    public static String capSentences(final String text) {

        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
