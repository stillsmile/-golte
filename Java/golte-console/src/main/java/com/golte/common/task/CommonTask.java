package com.golte.common.task;

import com.golte.common.exception.ServiceException;
import com.golte.common.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CommonTask {
    private static final Logger log = LoggerFactory.getLogger(CommonTask.class);

    @Autowired
    private TaskService taskService;


    /**
     * @Description:发送合同变更待审批消息 每天0点10分执行
     * @author wyf
     * @date 2019/4/15
     */
    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void sendChangeMessage() {
        try {
            log.info("发送合同变更待审批消息 开始");
            taskService.sendChangeMessage();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @Description:发送合同终止待审批消息 每天0点10分执行
     * @author wyf
     * @date 2019/4/15
     */
    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void sendStopMessage() {
        try {
            log.info("发送合同终止待审批消息 开始");
            taskService.sendStopMessage();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @Description:发送回款延期待审批消息 每天0点10分执行
     * @author wyf
     * @date 2019/4/15
     */
    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void sendDelayMessage() {
        try {
            log.info("发送回款延期待审批消息 开始");
            taskService.sendDelayMessage();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @Description:发送提前7日未回款提醒 每天0点10分执行
     * @author wyf
     * @date 2019/4/15
     */
    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void sendAdvanceRemind() {
        try {
            log.info("发送提前7日未回款提醒 开始");
            taskService.sendAdvanceRemind();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @Description:发送延迟7日未回款提醒 每天0点10分执行
     * @author wyf
     * @date 2019/4/15
     */
    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void sendDelayRemind() {
        try {
            log.info("发送延迟7日未回款提醒 开始");
            taskService.sendDelayRemind();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @Description:更新回款状态 每天0点10分执行
     * @author wyf
     * @date 2019/4/15
     */
    @Scheduled(cron = "0 10 0 1/1 * ?")
    public void updatePaybackStatus() {
        try {
            log.info("更新回款状态 开始");
            taskService.updatePaybackStatus();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
