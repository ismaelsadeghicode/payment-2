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
 * @author Ismael Sadeghi, 6/2/2019 5:41 PM
 */
@Component
public class TotalTransactionAmountMciService {

    private final LoggingRepository loggingRepository;

    @Autowired
    public TotalTransactionAmountMciService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public int findTotalTransactionAmountMci() {
        Date dailyDate = DateUtils.truncate(new Date(), Calendar.DATE);
        long today = dailyDate.getTime();
        List<DirectTopup> directTopups = loggingRepository.findByRequestDateTopupGreaterThanEqual(today);
        return directTopups.stream()
                .filter(d -> d.getMethod() == 3 && d.getStatus().equals(true))
                .mapToInt(DirectTopup::getAmount)
                .sum();
    }
}
