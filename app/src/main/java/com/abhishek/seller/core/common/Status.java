package com.abhishek.seller.core.common;

public enum Status {
    LOADING,
    SUCCESS,
    FAIL,
    AUTH_FAIL,
    FAIL_400,
    FAIL_DB,
    NO_DATA,
    ERROR,
    ITEMS_KITCHEN,
    ITEMS_FILTERS,
    DEFAULT_ERROR,
    GET_LOGIN_PIN,
    GET_NUMBER_VERIFIED,
    EDIT_PHONE_NUMBER,
    ERROR_OTP,
    GRANTED,
    ON_SHOW_DATA,
    ON_SHOW_ERROR,
    ON_SHOW_NO_INTERNET,
    ON_SHOW_SHIMMER,

    OK,
    HIDE_KEYBOARD,
    NO_INTERNET,
    DEFAULT_MSG,
    HORIZONTAL,
    ERROR_DIALOG_OK_CALLBACK,
    SAVE_ADDRESS,


    //SignUp MODULE
    VERSION_CHECK,
    CHANGE_PIN,
    INVALID_OTP,
    INVALID_PIN,
    AGREE_TERMS_CONDITION,
    INVALID_PASSWORD,
    MOPBILE_VERIFY,
    COUNTRY_PICKER,
    OTP_VERIFY,
    RESEND_OTP,
    SET_PIN,
    RESET_PIN,
    FORGOT_PIN_RESEND_OTP,
    FORGOT_PIN_OTP_VERIFY,
    FORGOT_CLICK,
    USER_LOGOUT,
    ABOUT_US,
    VERIFY_PIN,
    USER_SETTINGS,

    VERIFY_LOGIN_PIN,
    NEXT_CLICK,
    BACK_CLICK,
    RESEND_CLICK,
    USER_REWARDS,
    REDEEM_OUTLET,
    REDEEMABLE_OUTLET,

    //Profile
    PROFILE_PIC_CLICK,
    EDIT_CLICK_CHECK,
    FROM_COUNTRY,
    FROM_NATIONALITY,
    COUNTRY_NATIONALITY,
    UPLOAD_PROFILE_IMAGE,
    EDIT_PROFILE,
    VIEW_PROFILE_API,
    UPDATE_PROFILE_API,
    GENDER_ERROR,
    ERROR_MOBILE_ZERO,
    ERROR_IMAGE,
    ERROR_FNAME,
    ERROR_LNAME,
    ERROR_CITY,
    FLAG_CLICK,
    ERROR_MOBILE,
    EMPTY_EMAIL,
    EMPTY_NAME,
    PERMANENT_DENIED,
    NORMAL_DENIED,
    DOB_ERROR,
    ERROR_AREA,
    ERROR_NATIONALITY,
    ERROR_COUNTRY,
    CANCEL_CLICK,
    UPDATE_CLICK,
    MODIFY_CLICK,
    DOB_CLICK,
    CLICK_COUNTRY,
    CLICK_NATIONALITY,
    CLICK_GENDER,
    CLICK_CHANGE_EMAIL,
    CLICK_CHANGE_PROFILE,
    CLICK_CHANGE_MOBILE_NUM,
    SAVED_CARDS,
    CLICK_SAVED_CARD,
    DELETE_CARD,
    SHARE_REFERRAL,
    REMOVE_IMAGE,
    SEARCH_CLICK,
    MAKETRUE,
    FILTER_CLICK, GET_CATEGORY, MAKEFALSE;

//
//
//
//        //permission Type
//        LOCATION,
//
//        //Status Fields
//        NO_INTERNET_CONNECTION,
//        DEFAULT_MSG,
//        CALL_API,
//        COUNT_ERROR,
//
//
//
//
//        // Login Module
//        EMPTY_FIRST_NAME,
//        EMPTY_LAST_NAME,
//        EMAIL_ERROR,
//        EMPTY_PASSWORD,
//        C_PASSWORD_ERROR,
//        PASSWORD_ERROR,
//        MOBILE_ERROR,
//        DOB_ERROR,
//        GENDER_ERROR,
//        RELATION_ERROR,
//        ANNIVERSARY_ERROR,
//        EMAIL_SUBSCRIPTION_ERROR,
//        TERMS_ERROR,
//        OTP_ERROR,
//        RESEND_OTP,
//        VERIFY_OTP,
//        FORGOT_VERIFY_OTP,
//        FORGOT_RESEND_OTP,
//        DONE,
//        LOGIN,
//        SIGNUP,
//        SEARCH_ERROR,
//        AGE_ERROR,
//        FETCH_PROFILE,
//
//        // Categories
//
//        FETCH_CATEGORY_LIST,
//        FETCH_PRODUCT_LIST,
//        FETCH_SHORTING_LIST,
//        FETCH_PRODUCT_DETAIL,
//        FETCH_CONFIGURABLE_PRODUCT_DETAIL,
//
//
//        FETCH_WISH_LIST,
//        FETCH_CART_LIST,
//        FETCH_CART_TOTAL,
//        FETCH_CART_ID,
//        ADD_TO_BASKET,
//        CONFIGURE_ITEM,
//        NOTIFY,
//        INCREASE_ITEM,
//        DECREASE_ITEM,
//        REMOVE_ITEM,
//        ZERO_ITEM,
//        EMPTY_BASKET,
//        WISHLIST,
//        ADD_ITEM_TO_WISHLIST,
//        REMOVE_ITEM_TO_WISHLIST,
//
//
//
//
//
//
//
//
//
//        //profile screen
//        CUSTOMER_PROFILE,
//        EDIT_NAME,
//        EDIT_BEAUTY_INFO,
//        EDIT_EMAIL,
//        EDIT_MOBILE_NUMBER,
//        EDIT_PASSWORD,
//        EDIT_DOB,
//        EDIT_GENDER,
//        EDIT_RELATIONSHIP_STATUS,
//        EDIT_CITY,
//        ERROR_DOB,
//        ERROR_FNAME,
//        ERROR_LNAME,
//        ERROR_CITY,
//        ERROR_NEWEMAIL,
//        ERROR_CONFIRMEMAIL,
//        ERROR_EMAIL_CONFIRMATION,
//        EMPTY_ERROR_NEW_EMAIL,
//        EMPTY_ERROR_CONFIRM_EMAIL,
//        EMPTY_ERROR_MOBILE_NUMBER,
//        EMPTY_ERROR_CONFIRM_MOBILE_NUMBER,
//        EMPTY_ERROR_MOBILE_NUMBER_CONFIRMATION,
//        CURRENT_PASSWORD_EMPTY_ERROR,
//        NEW_PASSWORD_EMPTY_ERROR,
//        CONFIRM_PASSWORD_EMPTY_ERROR,
//        CURRENT_PASSWORD_ERROR,
//        CONFIRM_PASSWORD_ERROR,
//        PASSWORD_CONFIRMATION_ERROR,
//        EMPTY_ADDRESS_ERROR,
//        EMPTY_COUNTRY_ERROR,
//        EMPTY_STATE_ERROR,
//        EMPTY_PINCODE_ERROR,
//        SELECTED_ADDRESS,
//
//     HIDE_KEYBOARD,
//
//
//
//        //home toolbar
//        BASKET,
//        NOTIFICATION,
//        HUMBURGER,
//
//
//        //Dashbord Module
//        HOME_DATA,
//        NAVIGATION_ITEM_CLICK,
//        SEARCH,
//        RESET_PASSWORD,
//        MAGENTO_LOGIN,
//        MAGENTO_TOKEN,
//        RECIPROCII_TOKEN,
//        INSTA_REFRESH_TOKEN,
//        INSTA_MEDIA_ID,
//        INSTA_MEDIA_DETAIL,
//
//
//        // More
//
//        QUIZ_LIST,
//        QUIZ_DETAIL,
//        QUIZ_RESULT,
//        ABOUT_US,
//
//
//
//        //OrderDetails
//        ADD_BASKET_CLICK,
//        RETURN_EXCHANGE_CLICK,
//        RATE_REVIEW_CLICK,
//        CANCEL_ITEMS,
//
//        //my order screen
//        VIEW_MORE_ORDER_DETAILS_CLICK,
//        EXPAND_ORDER,
//        TRACK_SHIPMENT_CLICK,
//        FILTER_CLICK,
//        REORDER_CLICK,
//        //exchange return screen
//        HELP_CLICK,
//        EXCHANGE_CLICK,
//        RETURN_CLICK,
//        RETURN_REASON,
//        RETURN_RESOLUTION,
//        RETURN_CONDITION,
//
//        //cancel Item
//        CANCEL_CONFIRM_CLICK,
//        CANCEL_REASON_CLICK,
//
//
//        //my rewards
//        REWARDS_FOR_ME_CLICK,
//        REWARDS_ACTIVITY_CLICK,
//
//
//
//        //rate and review
//        SUBMIT_RATE_REVIEW_CLICK,
//
//        //checkout screen
//        EXPAND_DELIVERY_METHOD,
//        PICKUP_STORE_CLICK,
//        PROCEED_PAY_CLICK,
//
//        FETCH_CHECKSUM,
//        PLACE_ORDER,
//
//
//        //address screen
//        ADD_NEW_ADDRESS,
//        EDIT_ADDRESS_CLICK,
//        REMOVE_ADDRESS_CLICK,
//        API_ADD_ADDRESS,
//        API_COUNTRY_LIST,
//        API_EDIT_ADDRESS,
//        API_REMOVE_ADDRESS,
//        API_ADDRESS_LIST,
//        SAVE_DEFAULT_ADDRESS_CLICK,
//
//
//        //citybottom
//        CITY_PICKER,
//
//
//       //NotifyOutStock
//        API_NOTIFYSTOCK,
//        Update_Profile,
//        Update_Password,
//        Update_Contact,
//
//
//        //deliverymethod(Checkout screen)
//        GET_DELIVERY_METHOD_API,
//        GET_PAYMENT_METHOD_API,
//
//        //OtpVerification for order
//        API_OTP_VERIFY_ORDER,
//        API_GENERATE_OTP_COD,
//        API_ORDER_STATUS,
//
//        //rewards
//        API_IS_MEMBER,
//
//        //My order List
//
//        API_MY_ORDER_LIST,
//        API_REORDER,
//        API_ORDER_DETAILS,
//        API_CANCEL_ORDER,
//        API_CANCEL_REASON,
//
//
//        DOB_EIGHTEEN_ERROR,
//        DOB_OK,HOME_LOGO,
//        ITEM_CLICK_SEARCH,
//        DROP_DOWN_RETURN,
//        CONDITION_CLICK,
//        REASON_CLICK,
//        RESOLUTION_CLICK,
//        REASON,
//        CONDITION,
//        RESOLUTION,
//        RETURN_ORDER,
//        EMPTY_CONDITION,
//        EMPTY_RESOLUTION,
//        EMPTY_REASON,
//        VALID_RETURN,
//        RETURN_LIST,
//        R_COUPON_LIST,
//        PAST_ORDER,
//        BEAUTY_OPTION,
//        NEXT,
//        BACK,
//        ANSWER_ERROR,
//        TERM_API,
//        SKINDATA,
//        M_COUPON_LIST,
//        COUPON_LIST_ERROR,
//        APPLY_COUPON,
//        POINT_APPLY,
//        REMOVE_POINT,
//        POINT_APPLY_ERROR,
//        REMOVE_COUPONS,
//        FREE_SAMPLE,
//        REWARD_ME,
//        AGE_CLICK,
//        INCENTIVE_CLICK,
//        EYE_CLICK,
//        BEAUTY_CLICK,
//        SKIN_CLICK,
//        DIALOG,
//
//        //bazar voice
//        BAZAR_VOICE_SUCCESS,
//        BAZAR_VOICE_FAILURE,
//        TITLE_ERROR,
//        NICKNAME_ERROR,
//        REVIEW_ERROR,
//
//
//        //Offer
//
//        OFFER_LIST


}