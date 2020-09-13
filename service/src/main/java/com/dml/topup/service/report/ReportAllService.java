package com.dml.topup.service.report;

import com.dml.topup.data.response.Page;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.repository.LoggingRepository;
import com.dml.topup.service.charge.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Ismael Sadeghi, 6/11/2019 9:39 AM
 */
@Component
public class ReportAllService {

    private final LoggingRepository loggingRepository;

    public ReportAllService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public Page<DirectTopup> findAllReport(int page, int size) {
        org.springframework.data.domain.Page<DirectTopup> in = loggingRepository.findAllByOrderByRequestDateTimeTopupDesc(new PageRequest(page, size));
        return Service.getDirectTopupPage(in);
    }
}
