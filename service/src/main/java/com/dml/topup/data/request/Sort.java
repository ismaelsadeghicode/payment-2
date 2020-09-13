package com.dml.topup.data.request;

import lombok.Data;

/**
 * @author Ismael Sadeghi, 6/19/2019 12:15 PM
 */
@Data
public class Sort {
    private SortDirection direction;
    private String forField;

    public Sort(SortDirection direction, String forField) {
        this.direction = direction;
        this.forField = forField;
    }
}
