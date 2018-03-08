package com.sptwin.sptserver.config;

import com.sptwin.sptserver.base.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledConfig {

	@Autowired
	private ScheduledService scheduledService;

	/**
	 * fixedRate:每隔多少时间就执行任务，不管该任务是否执行完成
	 * fixedDelay:每次【执行完任务】之后间隔多久再次执行该任务
	 * initialDelay :初次执行任务之前需要等待的时间
	 */
	 /*@Scheduled(fixedRate = 3000)
	 public void fixedRateTest(){
		 System.out.println("fixedRate测试-----------------");
	 }*/
	/**
	 * 实时亏盈任务
	 */
	/*@Scheduled(fixedDelay = 3000)
	public void realTimeProfitLoss(){
		scheduledService.realTimeProfitLoss();
	}*/
	/**
	 * 每天15：03，将持资金表中的记录 填写到历史资金表中。
	 */
	@Scheduled(cron = "0 3 15 ? * *")
	public void historyTask() {
		scheduledService.historyTask();
	}

	/**
	 * 每天15：04，结算任务。
	 */
	@Scheduled(cron = "0 4 15 ? * *")
	public void settlementTask() {
		scheduledService.settlementTask();
	}

	/**
	 * 每天15：05，代理结算任务。
	 */
	@Scheduled(cron = "0 5 15 ? * *")
	public void agentSettlementTask() {
		scheduledService.agentSettlementTask();
	}
}
