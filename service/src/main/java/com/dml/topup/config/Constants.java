package com.dml.topup.config;

/**
 * Application constants.
 *
 * @author Ismael Sadeghi
 */
public final class Constants {

    public static final String SETAREYEK_URL = "setareyek.url";
    public static final String MCI_REST_ORDER_URL = "mci.rest.order.url";
    public static final String MTN_RIGHTEL_REST_ORDER_URL = "mtn.rightel.rest.order.url";
    public static final String MCI_REST_SUBMIT_URL = "mci.rest.submit.url";
    public static final String MTN_RIGHTEL_REST_SUBMIT_URL = "mtn.rightel.rest.submit.url";
    public static final String MCI_REST_GET_REMAINED_BALANCE_URL = "mci.rest.get.remained.balance.mci.url";
    public static final String MTN_RIGHTEL_REST_GET_REMAINED_BALANCE_URL = "mtn.rightel.rest.get.remained.balance.mci.url";
    public static final String MCI_REST_GET_TRANSACTION_INQUIRY = "mci.rest.get.transaction.inquiry"; // service inquiry setareyek
    public static final String MTN_RIGHTEL_REST_GET_TRANSACTION_INQUIRY = "mtn.rightel.rest.get.transaction.inquiry";  // service inquiry rightel setareyek
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String PARSIAN_KEY = "parsian.key";
    public static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    public static final int ORDER_TYPE_VALUE = 1;
    public static final String AUTHENTICATION_BASIC = "Basic %s";
    public static final String DATA_HEADER_VALUE = "%s";
    public static final String SIMPLE_DATE_FORMAT = "MM-dd-yyyy";
    public static final String FULL_DATE_PATTERN = "MM-dd-yyyy HH:mm:ss";
    public static final String REQUEST_FORMAT_DATE = "yyyy-MM-dd";

    // SETAREYEK INFO
    public static final String SETAREYEK_USERNAME = "setareyek.username";
    public static final String SETAREYEK_PASSWORD = "setareyek.password";
    public static final String SETAREYEK_UID = "setareyek.uid";
    public static final String SETAREYEK_MODULUS = "setareyek.modulus";
    public static final String SETAREYEK_EXPONENT = "setareyek.exponent";
    public static final String ALGORITHM_RSA = "algorithm.rsa";

    // USERNAME AND PASWORD
    public static final String PARSIAN_AUTHENTICATION_USERNAME = "parsian.authentication.username";
    public static final String PARSIAN_AUTHENTICATION_PASSWORD = "parsian.authentication.password";
    public static final String AUTHORIZATION = "Authorization";

    public Constants() {
    }
}
