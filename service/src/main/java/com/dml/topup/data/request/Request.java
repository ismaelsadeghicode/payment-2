package com.dml.topup.data.request;

import lombok.Data;

import java.util.Date;

@Data
public abstract class Request {
    private Pageable pageable;

    Date postageDate = new Date();

}
