package com.dml.topup.service.report;

import com.dml.topup.domain.DirectTopup;
import com.dml.topup.repository.LoggingRepository;
import com.dml.topup.service.charge.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Ismael Sadeghi, 6/11/2019 3:20 PM
 */
@Component
public class ChargeStatusOfPhoneNumberService {

    private final LoggingRepository loggingRepository;

    public ChargeStatusOfPhoneNumberService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public com.dml.topup.data.response.Page<DirectTopup> getChargeStatusOfPhoneNumber(long subscriberNo, int page, int size) {
        Page<DirectTopup> in = loggingRepository.findBySubscriberNo(subscriberNo, new PageRequest(page, size));
        return Service.getDirectTopupPage(in);
    }
}
