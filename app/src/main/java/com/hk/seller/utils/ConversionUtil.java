package com.hk.seller.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConversionUtil {
    INSTANCE;


    public int convertStringToInteger(String value) {
        int val = 0;
        try {
            if (value != null && !value.isEmpty()) {
                val = Integer.parseInt(value);
                return val;
            } else {
                return val;
            }
        } catch (Exception e) {
            return val;
        }
    }

    public double convertStringToDouble(String value) {
        double val = 0;
        try {
            if (value != null && !value.isEmpty()) {
                val = Double.parseDouble(value);
                return val;
            } else {
                return val;
            }
        } catch (Exception e) {
            return val;
        }
    }

    public String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    public String twoDigitAfterDecimal(String num) {
        try {
            if (num != null) {
                return String.format("%.2f", Double.valueOf(num));
            }
        } catch (Exception e) {
            return num;
        }
        return num;
    }


    public String convertDoubleToInteger(Double num) {
        try {
            if (num != null) {
                return String.valueOf(((Number) num).intValue());
            }
        } catch (Exception e) {
            return num.toString();
        }
        return num.toString();
    }


    public String convertRoundOff(Double num)
    {
        try {
            if (num != null) {
                return String.valueOf(Math.round(num));
            }
        } catch (Exception e) {
            return num.toString();
        }
        return num.toString();
    }

}