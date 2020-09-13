package com.dml.topup.service.report;

import com.dml.topup.config.Constants;
import com.dml.topup.data.response.report.TotalDailyTransactionsResponse;
import com.dml.topup.domain.DirectTopup;
import com.dml.topup.repository.LoggingRepository;
import com.dml.topup.service.charge.Service;
import com.ghasemkiani.util.icu.PersianDateFormat;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.valueOf;

@Component
public class DailyReportService {

    private final LoggingRepository loggingRepository;
    private SimpleDateFormat requestFormatDate = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);

    @Autowired
    public DailyReportService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public com.dml.topup.data.response.Page<DirectTopup> filter(Map<String, String> requestParams) {
        long id = 0;
        Boolean status = null;
        long subscriberNo = 0;
        int amount = 0;
        int methodStart = 0;
        int methodEnd = 0;
        long requestDateTopup = 0;
        int size = Integer.parseInt(requestParams.get("size"));
        int page = Integer.parseInt(requestParams.get("page"));
        Direction direction = null;
        String propertie;
        if (requestParams.get("direction") == null || !requestParams.get("direction").isEmpty()) {
            direction = Direction.DESC;
        } else {
            direction = Direction.fromString(requestParams.get("direction"));
        }

        if (requestParams.get("propertie") == null || !requestParams.get("propertie").isEmpty()) {
            propertie = "id";
        } else {
            propertie = requestParams.get("propertie");
        }


        if (requestParams.get("id") != null && !requestParams.get("id").isEmpty()) {
            id = Long.parseLong(requestParams.get("id"));
        }
        if (requestParams.get("status") != null && !requestParams.get("status").isEmpty()) {
            status = valueOf(requestParams.get("status"));
        }
        if (requestParams.get("subscriberNo") != null && !requestParams.get("subscriberNo").isEmpty()) {
            subscriberNo = Long.valueOf(requestParams.get("subscriberNo"));
        }
        if (requestParams.get("amount") != null && !requestParams.get("amount").isEmpty()) {
            amount = Integer.parseInt(requestParams.get("amount"));
        }
        if (requestParams.get("method") != null && !requestParams.get("method").isEmpty()) {
            if (requestParams.get("method").equals("IRANCEL")) {
                methodStart = 21;
                methodEnd = 22;
            } else if (requestParams.get("method").equals("RIGHTEL")) {
                methodStart = 41;
                methodEnd = 42;
            } else if (requestParams.get("method").equals("MCI")) {
                methodStart = 3;
                methodEnd = 3;
            }
        }
        if (requestParams.get("requestDateTopup") != null && !requestParams.get("requestDateTopup").isEmpty() && requestParams.get("requestDateTopup").length() >= 10) {
            try {
                PersianDateFormat pdformater1 = new PersianDateFormat(Constants.REQUEST_FORMAT_DATE);
                Date persianDate = pdformater1.parse(requestParams.get("requestDateTopup"));
                requestDateTopup = requestFormatDate.parse(requestFormatDate.format(persianDate)).getTime();
            } catch (ParseException ex) {
                requestDateTopup = 0;
            }
        }

        // Todo after version, refactoring
        Page<DirectTopup> in = null;
        if (status != null) {
            if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatus(status, new PageRequest(page, size, direction, propertie));
            } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndAmountAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, subscriberNo, amount, methodStart, methodEnd, requestDateTopup);
            } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), status, subscriberNo, amount, methodStart, methodEnd);
            } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndAmount(new PageRequest(page, size, direction, propertie), status, subscriberNo, amount);
            } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNo(new PageRequest(page, size, direction, propertie), status, subscriberNo);
            } else if (id > 0) {
                in = loggingRepository.findAllByIdAndStatus(new PageRequest(page, size, direction, propertie), id, status);
            } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
                in = loggingRepository.findByMethodBetweenAndStatus(new PageRequest(page, size, direction, propertie), methodStart, methodEnd, status);
            } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
                in = loggingRepository.findBySubscriberNoAndStatus(new PageRequest(page, size, direction, propertie), subscriberNo, status);
            } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
                in = loggingRepository.findByStatusAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, requestDateTopup);
            } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
                in = loggingRepository.findByStatusAndRequestDateTopupAndAmount(new PageRequest(page, size, direction, propertie), status, requestDateTopup, amount);
            } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
                in = loggingRepository.findByStatusAndRequestDateTopupAndAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), status, requestDateTopup, amount, methodStart, methodEnd);
            } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, subscriberNo, requestDateTopup);
            } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndAmountAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, subscriberNo, amount, requestDateTopup);
            } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndMethodBetween(new PageRequest(page, size, direction, propertie), status, subscriberNo, methodStart, methodEnd);
            } else if (id > 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByIdAndStatusAndSubscriberNoAndAmountAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), id, status, subscriberNo, amount, methodStart, methodEnd, requestDateTopup);
            } else if (id > 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByIdAndStatusAndSubscriberNoAndAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), id, status, subscriberNo, amount, methodStart, methodEnd);
            } else if (id > 0 && subscriberNo > 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByIdAndStatusAndSubscriberNoAndAmount(new PageRequest(page, size, direction, propertie), id, status, subscriberNo, amount);
            } else if (id > 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByIdAndStatusAndSubscriberNo(new PageRequest(page, size, direction, propertie), id, status, subscriberNo);
            } else if (id > 0) {
                in = loggingRepository.findAllByIdAndStatus(new PageRequest(page, size, direction, propertie), id, status);
            } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), status, subscriberNo, amount, methodStart, methodEnd);
            } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByStatusAndAmountAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, amount, methodStart, methodEnd, requestDateTopup);
            } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
                in = loggingRepository.findAllByStatusAndAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), status, amount, methodStart, methodEnd);
            } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByStatusAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, methodStart, methodEnd, requestDateTopup);
            } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
                in = loggingRepository.findAllByStatusAndSubscriberNoAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), status, subscriberNo, methodStart, methodEnd, requestDateTopup);
            } else {
                in = loggingRepository.findAllByStatus(status, new PageRequest(page, size, direction, propertie));
            }
        } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllBySubscriberNoAndAmountAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), subscriberNo, amount, methodStart, methodEnd, requestDateTopup);
        } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllBySubscriberNoAndAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), subscriberNo, amount, methodStart, methodEnd);
        } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllBySubscriberNoAndAmount(new PageRequest(page, size, direction, propertie), subscriberNo, amount);
        } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllBySubscriberNo(subscriberNo, new PageRequest(page, size, direction, propertie));
        } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllByAmountAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), amount, methodStart, methodEnd, requestDateTopup);
        } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllByAmountAndMethodBetween(new PageRequest(page, size, direction, propertie), amount, methodStart, methodEnd);
        } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllByAmount(new PageRequest(page, size, direction, propertie), amount);
        } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllByMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), methodStart, methodEnd, requestDateTopup);
        } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllByMethodBetween(new PageRequest(page, size, direction, propertie), methodStart, methodEnd);
        } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllByRequestDateTopup(new PageRequest(page, size, direction, propertie), requestDateTopup);
        } else if (id == 0 && subscriberNo == 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
            in = loggingRepository.findAll(new PageRequest(page, size, direction, propertie));
        } else if (id > 0) {
            in = loggingRepository.findById(new PageRequest(page, size, direction, propertie), id);
        } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup == 0) {
            in = loggingRepository.findBySubscriberNo(new PageRequest(page, size, direction, propertie), subscriberNo);
        } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllBySubscriberNoAndRequestDateTopup(new PageRequest(page, size, direction, propertie), subscriberNo, requestDateTopup);
        } else if (id == 0 && subscriberNo == 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllByAmountAndRequestDateTopup(new PageRequest(page, size, direction, propertie), amount, requestDateTopup);
        } else if (id == 0 && subscriberNo > 0 && amount > 0 && methodStart == 0 && methodEnd == 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllBySubscriberNoAndAmountAndRequestDateTopup(new PageRequest(page, size, direction, propertie), subscriberNo, amount, requestDateTopup);
        } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup > 0) {
            in = loggingRepository.findAllBySubscriberNoAndMethodBetweenAndRequestDateTopup(new PageRequest(page, size, direction, propertie), subscriberNo, methodStart, methodEnd, requestDateTopup);
        } else if (id == 0 && subscriberNo > 0 && amount == 0 && methodStart > 0 && methodEnd > 0 && requestDateTopup == 0) {
            in = loggingRepository.findAllBySubscriberNoAndMethodBetween(new PageRequest(page, size, direction, propertie), subscriberNo, methodStart, methodEnd);
        } else {
            in = loggingRepository.findAll(new PageRequest(page, size, direction, propertie));
        }
        return Service.getDirectTopupPage(in);
    }

    public TotalDailyTransactionsResponse totalDailyTransactions() {
        TotalDailyTransactionsResponse response = new TotalDailyTransactionsResponse();
        Date dailyDate = DateUtils.truncate(new Date(), Calendar.DATE);
        long today = dailyDate.getTime();
        List<DirectTopup> directTopups = loggingRepository.findAllByRequestDateTopup(today); // Todo change Quary
        int sumMtn = 0;
        int sumMci = 0;
        int sumRightel = 0;
        for (DirectTopup directTopup : directTopups) {
            if (directTopup.getMethod() == 3) {
                sumMci = sumMci + directTopup.getAmount();
            } else if (directTopup.getMethod() == 21 && directTopup.getMethod() == 22) {
                sumMtn = sumMtn + directTopup.getAmount();
            } else if (directTopup.getMethod() == 41 && directTopup.getMethod() == 42) {
                sumRightel = sumRightel + directTopup.getAmount();
            }
        }

        response.setMci(sumMci);
        response.setMtn(sumMtn);
        response.setRightel(sumRightel);
        return response;
    }

}
