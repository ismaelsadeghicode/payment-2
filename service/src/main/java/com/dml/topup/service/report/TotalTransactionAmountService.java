package com.dml.topup.service.report;

import com.dml.topup.domain.DirectTopup;
import com.dml.topup.repository.LoggingRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ismael Sadeghi, 6/2/2019 6:45 PM
 */
@Component
public class TotalTransactionAmountService {

    private final LoggingRepository loggingRepository;

    @Autowired
    public TotalTransactionAmountService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public int findTotalTransactionAmount() {
        Date dailyDate = DateUtils.truncate(new Date(), Calendar.DATE);
        long today = dailyDate.getTime();
        List<DirectTopup> directTopups = loggingRepository.findByRequestDateTopupGreaterThanEqual(today);
        return directTopups.stream()
                .filter(d -> d.getStatus().equals(true))
                .mapToInt(DirectTopup::getAmount)
                .sum();
    }
}
