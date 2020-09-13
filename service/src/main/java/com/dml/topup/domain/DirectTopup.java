package com.dml.topup.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Ismael Sadeghi
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "DirectTopup")
@SequenceGenerator(
        name = "direct-topup-sequence",
        sequenceName = "DIRECT_TOPUP_SEQ",
        allocationSize = 1000, initialValue = 10000)
public class DirectTopup extends DomainEntity implements Comparable<DirectTopup> {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "direct-topup-sequence")
    private Long id;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "subscriberNo")
    private Long subscriberNo;
    @Column(name = "amount")
    private int amount;
    @Column(name = "method")
    private int method;
    @Column(name = "resNum")
    private int resNo;
    @Column(name = "responseCodeOrder")
    private int resCodeOrder;
    @Column(name = "responseCodeSubmit")
    private int resCodeSubmit;
    @Column(name = "messageId")
    private String messageId;
    @Column(name = "errorCode")
    private String errorCode;
    @Column(name = "postageDate")
    private Date postageDate;
    @Column(name = "requestDateTimeTopup")
    private Long requestDateTimeTopup;
    @Column(name = "requestDateTopup")
    private Long requestDateTopup;
    @Column(name = "traceNoTopup")
    private String traceNoTopup;

    @Override
    public int compareTo(DirectTopup directTopup) {
        Long code = directTopup.getId();
        return this.id.compareTo(code);
    }
}
