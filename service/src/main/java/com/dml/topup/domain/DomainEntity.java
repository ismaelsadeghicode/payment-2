package com.dml.topup.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by postageDate.
 *
 * @author Ismael Sadeghi
 */
@Data
@MappedSuperclass
public abstract class DomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "createdDate")
    private ZonedDateTime createdDate = ZonedDateTime.now();

}
