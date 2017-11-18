package com.bitirme.taksishare.services;

import com.bitirme.taksishare.db.NotificationDao;
import com.bitirme.taksishare.db.UsersDao;
import com.bitirme.taksishare.mvc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by exper on 03.06.2017.
 */

@Component
public class NotificationService {

    @Autowired
    NotificationDao notificationDao;

    @Autowired
    UsersDao userDao;

    public void createNotification (Integer receiverId , Integer senderId, Integer taxiJourneyId) {

        String name = userDao.getUserById(senderId).getName();
        Notification notification = new Notification();
        notification.setReceiverId(receiverId);
        notification.setSenderId(senderId);
        notification.setTaxiJourneyId(taxiJourneyId);
        notification.setMessage(name + " taksi isteğinle ilgilendi, iletişime geçmek için tıkla!");
        notification.setDate(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));

        Integer newNotificationId = notificationDao.createNotification(notification);

        if (newNotificationId > 0) {
            notification.setId(newNotificationId);
        }

    }


    public List<Notification> getNotificationByReceiverId (Integer receiverId) {

        return notificationDao.getNotificationByReceiverId(receiverId);

    }

}
