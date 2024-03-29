package com.notification.service.Impl;

import com.notification.exception.NotificationException;
import com.notification.queue.QueueStore;
import com.notification.request.Notification;
import com.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements NotificationService<Notification> {
    @Override
    public void send(Notification notification) throws NotificationException {
        if (notification.getSms() != null) {
            notification.getSms().setSmsId(notification.getId());
            QueueStore.publish(notification.getSms());
            log.info("message publish to queue for Mobile no. " + notification.getSms().getMobile());

        }
    }
}
