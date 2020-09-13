package com.dml.topup.data.request;

import lombok.Data;

/**
 * @author Ismael Sadeghi, 6/19/2019 12:14 PM
 */
@Data
public class Pageable {
    private int page;
    private int size = 200;
    private Sort sort;
}
