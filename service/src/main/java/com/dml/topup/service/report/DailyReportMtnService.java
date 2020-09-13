package com.dml.topup.service.report;

import com.dml.topup.domain.DirectTopup;
import com.dml.topup.repository.LoggingRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Ismael Sadeghi, 6/2/2019 6:57 PM
 */
@Component
public class DailyReportMtnService {

    private final LoggingRepository loggingRepository;

    @Autowired
    public DailyReportMtnService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public Flux<DirectTopup> findAllTransactions() {
        Date dailyDate = DateUtils.truncate(new Date(), Calendar.DATE);
        long today = dailyDate.getTime();
        return Flux.fromIterable(loggingRepository.findByRequestDateTopupGreaterThanEqual(today)
                .stream()
                .filter(d -> d.getMethod() == 21 && d.getStatus().equals(true))
                .collect(Collectors.toList()));
    }

}
