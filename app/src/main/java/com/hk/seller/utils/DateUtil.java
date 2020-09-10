package com.hk.seller.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.hk.seller.core.common.AppConstant.DateFormats.DD_MMM;
import static com.hk.seller.core.common.AppConstant.DateFormats.DD_MM_YY;
import static com.hk.seller.core.common.AppConstant.DateFormats.DD_MM_YYYY;
import static com.hk.seller.core.common.AppConstant.DateFormats.DD_MM_YYYY_;
import static com.hk.seller.core.common.AppConstant.DateFormats.YYYY_MM_DD;
import static com.hk.seller.core.common.AppConstant.DateFormats.yyyy_MM_dd_HH_mm_ss;


public class DateUtil {

    private DateUtil(){}

    public static String getCurrentDate(String returnFormat){

        String formatDate = null;
        SimpleDateFormat returnSdf = new SimpleDateFormat(returnFormat, Locale.ENGLISH);

        switch (returnFormat) {

            case YYYY_MM_DD:
                formatDate = getCurrentFormatDate(returnSdf);
                break;

            case yyyy_MM_dd_HH_mm_ss:
                formatDate = getCurrentFormatDate(returnSdf);
                break;

            default:
                    formatDate = "N/A";
                    break;
        }

        return formatDate;
    }

    private static String getCurrentFormatDate(SimpleDateFormat returnSdf) {

        String formatDate = null;
        Date cominDate = new Date();
        try {
            formatDate = returnSdf.format(cominDate);

        } catch (Exception e) {
            //Do nothing here
        }
        return formatDate;
    }

    public static String convertDateFormat(String returnFormat, String comingFormat, String date) {

        String formatDate = null;
        SimpleDateFormat comingSdf = new SimpleDateFormat(comingFormat, Locale.ENGLISH);
        SimpleDateFormat returnSdf = new SimpleDateFormat(returnFormat, Locale.ENGLISH);

        switch (returnFormat) {

            case YYYY_MM_DD:

                formatDate = getStringFormatDate(date, comingSdf, returnSdf);

                break;

            case DD_MM_YYYY:

                formatDate = getStringFormatDate(date, comingSdf, returnSdf);

                break;
            case DD_MM_YYYY_:

                formatDate = getStringFormatDate(date, comingSdf, returnSdf);

                break;
            case DD_MM_YY:

                formatDate = getStringFormatDate(date, comingSdf, returnSdf);

                break;
            case DD_MMM:

                formatDate = getStringFormatDate(date, comingSdf, returnSdf);

                break;

            case yyyy_MM_dd_HH_mm_ss:
                formatDate = getStringFormatDate(date,comingSdf,returnSdf);
                break;

            default:
                formatDate = "N/A";
                break;
        }


        return formatDate;
    }

    private static String getStringFormatDate(String date, SimpleDateFormat comingSdf, SimpleDateFormat returnSdf) {

        String formatDate = null;
        Date cominDate;
        try {
            cominDate = comingSdf.parse(date);
            formatDate = returnSdf.format(cominDate);

        } catch (ParseException e) {
            //Do nothing here
        }
        return formatDate;
    }




    public static String getCurrentDateTime(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());

    return strDate;
    }

}
