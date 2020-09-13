package com.dml.topup.data.request;

import java.util.Locale;

/**
 * @author Ismael Sadeghi, 6/19/2019 12:15 PM
 */
public enum SortDirection {
    ASC, DESC;

    public static SortDirection fromString(String value) {

        try {
            return SortDirection.valueOf(value.toUpperCase(Locale.US));
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format(
                    "Invalid value '%s' for orders given! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
        }
    }
}
