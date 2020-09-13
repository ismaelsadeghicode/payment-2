package com.dml.topup.data.response;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Ismael Sadeghi, 6/19/2019 2:50 PM
 */
@Data
public class Page<T> {
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int number;
    private int size;
    private boolean hasContent;
    private Sort sort;
    private boolean first;
    private boolean last;
    private boolean next;
    private boolean previous;
    private List<T> content;
}
