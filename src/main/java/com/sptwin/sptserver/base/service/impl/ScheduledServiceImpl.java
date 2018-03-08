package com.sptwin.sptserver.base.service.impl;

import com.sptwin.sptserver.base.service.ScheduledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("scheduledService")
public class ScheduledServiceImpl implements ScheduledService {

    public static final Logger log = LoggerFactory.getLogger(ScheduledService.class);


    @Override
    public void historyTask() {

    }

    @Override
    public void settlementTask() {

    }

    @Override
    public void agentSettlementTask() {

    }
}
