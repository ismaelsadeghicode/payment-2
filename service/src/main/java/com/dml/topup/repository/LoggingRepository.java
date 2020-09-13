package com.dml.topup.repository;

import com.dml.topup.domain.DirectTopup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Charge entity.
 *
 * @author Ismael Sadeghi
 */
@Repository
public interface LoggingRepository extends JpaRepository<DirectTopup, Long> {

    Page<DirectTopup> findAll(Pageable pageable);

    Page<DirectTopup> findById(Pageable pageable, long id);

    Page<DirectTopup> findAllByAmount(Pageable pageable, int amount);

    List<DirectTopup> findAllByRequestDateTopup(long requestDateTopup);

    Page<DirectTopup> findAllByStatus(Boolean status, Pageable pageable);

    Page<DirectTopup> findBySubscriberNo(Pageable pageable, long subscriberNo);

    Page<DirectTopup> findBySubscriberNo(long subscriberNo, Pageable pageable);

    Page<DirectTopup> findAllBySubscriberNo(long subscriberNo, Pageable pageable);

    Page<DirectTopup> findAllByOrderByRequestDateTimeTopupDesc(Pageable pageable);

    List<DirectTopup> findByRequestDateTopupGreaterThanEqual(long requestDateTopup);

    Page<DirectTopup> findAllByIdAndStatus(Pageable pageable, long id, Boolean status);

    Page<DirectTopup> findAllByRequestDateTopup(Pageable pageable, long requestDateTopup);

    Page<DirectTopup> findAllByMethodBetween(Pageable pageable, int methodStart, int methodEnd);

    DirectTopup findByTraceNoTopupAndRequestDateTopup(String traceNoTopup, long requestDateTopup);

    Page<DirectTopup> findAllBySubscriberNoAndAmount(Pageable pageable, long subscriberNo, int amount);

    Page<DirectTopup> findBySubscriberNoAndStatus(Pageable pageable, long subscriberNo, Boolean status);

    Page<DirectTopup> findByStatusAndRequestDateTopup(Pageable pageable, Boolean status, long requestDateTopup);

    Page<DirectTopup> findByStatusAndRequestDateTopupAndAmount(Pageable pageable, Boolean status, long requestDateTopup, int amount);

    Page<DirectTopup> findByStatusAndRequestDateTopupAndAmountAndMethodBetween(Pageable pageable, Boolean status, long requestDateTopup, int amount, int methodStart, int methodEnd);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndRequestDateTopup(Pageable pageable, Boolean status, long subscriberNo, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndAmountAndRequestDateTopup(Pageable pageable, Boolean status, long subscriberNo, int amount, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndMethodBetween(Pageable pageable, Boolean status, long subscriberNo, int methodStart, int methodEnd);

    Page<DirectTopup> findAllBySubscriberNoAndMethodBetween(Pageable pageable, long subscriberNo, int methodStart, int methodEnd);

    Page<DirectTopup> findAllBySubscriberNoAndMethodBetweenAndRequestDateTopup(Pageable pageable, long subscriberNo, int methodStart, int methodEnd, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndMethodBetweenAndRequestDateTopup(Pageable pageable, Boolean status, int methodStart, int methodEnd, long requestDateTopu);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndMethodBetweenAndRequestDateTopup(Pageable pageable, Boolean status, long subscriberNo, int methodStart, int methodEnd, long requestDateTopu);

    Page<DirectTopup> findAllByStatusAndAmountAndMethodBetweenAndRequestDateTopup(Pageable pageable, Boolean status, int amount, int methodStart, int methodEnd, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndAmountAndMethodBetween(Pageable pageable, Boolean status, int amount, int methodStart, int methodEnd);

    Page<DirectTopup> findAllBySubscriberNoAndAmountAndRequestDateTopup(Pageable pageable, long subscriberNo, int amount, long requestDateTopup);

    Page<DirectTopup> findAllByAmountAndRequestDateTopup(Pageable pageable, int amount, long requestDateTopup);

    Page<DirectTopup> findAllBySubscriberNoAndRequestDateTopup(Pageable pageable, long subscriberNo, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndSubscriberNo(Pageable pageable, Boolean status, long subscriberNo);

    Page<DirectTopup> findAllByAmountAndMethodBetween(Pageable pageable, int amount, int methodStart, int methodEnd);

    Page<DirectTopup> findByMethodBetweenAndStatus(Pageable pageable, int methodStart, int methodEnd, Boolean status);

    Page<DirectTopup> findAllByIdAndStatusAndSubscriberNo(Pageable pageable, long id, Boolean status, long subscriberNo);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndAmount(Pageable pageable, Boolean status, long subscriberNo, int amount);

    Page<DirectTopup> findAllByMethodBetweenAndRequestDateTopup(Pageable pageable, int methodStart, int methodEnd, long requestDateTopup);

    Page<DirectTopup> findAllByIdAndStatusAndSubscriberNoAndAmount(Pageable pageable, long id, Boolean status, long subscriberNo, int amount);

    Page<DirectTopup> findAllBySubscriberNoAndAmountAndMethodBetween(Pageable pageable, long subscriberNo, int amount, int methodStart, int methodEnd);

    Page<DirectTopup> findAllByAmountAndMethodBetweenAndRequestDateTopup(Pageable pageable, int amount, int methodStart, int methodEnd, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndAmountAndMethodBetween(Pageable pageable, Boolean status, long subscriberNo, int amount, int methodStart, int methodEnd);

    Page<DirectTopup> findAllByIdAndStatusAndSubscriberNoAndAmountAndMethodBetween(Pageable pageable, long id, Boolean status, long subscriberNo, int amount, int methodStart, int methodEnd);

    Page<DirectTopup> findAllBySubscriberNoAndAmountAndMethodBetweenAndRequestDateTopup(Pageable pageable, long subscriberNo, int amount, int methodStart, int methodEnd, long requestDateTopup);

    Page<DirectTopup> findAllByStatusAndSubscriberNoAndAmountAndMethodBetweenAndRequestDateTopup(Pageable pageable, Boolean status, long subscriberNo, int amount, int methodStart, int methodEnd, long requestDateTopup);

    Page<DirectTopup> findAllByIdAndStatusAndSubscriberNoAndAmountAndMethodBetweenAndRequestDateTopup(Pageable pageable, long id, Boolean status, long subscriberNo, int amount, int methodStart, int methodEnd, long requestDateTopup);

}
