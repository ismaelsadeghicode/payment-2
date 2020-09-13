package com.dml.topup;

import com.dml.topup.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Running code after app starts
 *
 * @author Ismael Sadeghi
 */
@Component
public class AppStartUp {

    private final SecurityUtil securityUtil;

    @Autowired
    public AppStartUp(SecurityUtil securityUtil) {
        this.securityUtil = securityUtil;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        securityUtil.addAuthValue();
    }
}