package com.bitirme.taksishare.db;

/**
 * Created by exper on 03.06.2017.
 */

import com.bitirme.taksishare.mvc.models.mappers.NotificationMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import com.bitirme.taksishare.mvc.models.Notification;

import java.util.List;

public abstract class NotificationDao {

    @SqlUpdate("INSERT INTO notifications (receiverId , senderId , taxiJourneyId ,message ,date) VALUES (:notification.receiverId, :notification.senderId,:notification.taxiJourneyId,:notification.message, :notification.date)")
    @GetGeneratedKeys
    public abstract Integer createNotification(@BindBean("notification") Notification notification);

    @SqlQuery("SELECT * FROM notifications WHERE notifications.receiverId = :receiverId")
    @Mapper(NotificationMapper.class)
    public abstract List<Notification> getNotificationByReceiverId(@Bind("receiverId") Integer receiverId);


}
