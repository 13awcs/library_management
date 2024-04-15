package com.library.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    public static final Double TAX = 0.1;
    public static final String EMPTY_STR = "";
    public static final String SYSTEM_ADMIN = "SYSTEM_ADMIN";
    public static final String COMPANY_ADMIN = "COMPANY_ADMIN";
    public static final String STORE_ADMIN = "STORE_ADMIN";
    public static final int ROLE_TYPE_COMPANY = 1;
    public static final int ROLE_TYPE_STORE = 2;
    public static final String ASC = "ASC";
    public static final String GET_CHANGE_PASSWORD_URL = "/change-password";
    public static final String POST_CHANGE_PASSWORD_URL = "/api/v1/web/user/changePassword";
    public static final String LOGOUT = "/logout";
    public static final String WEB_USER = "WEB_USER";
    public static final String FORMAT_DATE_WITHOUT_SLASH = "yyyyMMdd";

    public static final String URL_S3 = "https://s3-ap-northeast-1.amazonaws.com/honegori/";

    public static final String SYMBOL_FOR_NUMBER_BOOKING_OF_BREAK_TIME = "BREAK";

    public static final String SUCCESS_MSG = "SUCCESS";

    public static final String EMPTY_STRING = "";

    public static final String LOCALHOST_PATH = "localhost";

    public static final String CLIENT_PATH = "http://localhost:8081";

    public static final String ACCESS_TOKEN_PARAM = "Authorization";

}
