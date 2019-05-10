package com.golte.common.task.service;

public interface TaskService {
    /**
     * @Description:发送合同变更待审批消息
     * @author wyf
     * @date 2019/4/15
     */
    void sendChangeMessage() throws Exception;

    /**
     * @Description:发送合同终止待审批消息
     * @author wyf
     * @date 2019/4/15
     */
    void sendStopMessage() throws Exception;

    /**
     * @Description:发送回款延期待审批消息
     * @author wyf
     * @date 2019/4/15
     */
    void sendDelayMessage() throws Exception;

    /**
     * @Description:发送提前7日未回款提醒
     * @author wyf
     * @date 2019/4/15
     */
    void sendAdvanceRemind() throws Exception;

    /**
     * @Description:发送延迟7日未回款提醒
     * @author wyf
     * @date 2019/4/15
     */
    void sendDelayRemind() throws Exception;

    /**
     * @Description:更新回款状态
     * @author wyf
     * @date 2019/4/15
     */
    void updatePaybackStatus() throws Exception;
}
