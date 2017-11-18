package com.bitirme.taksishare.mvc.models.mappers;

/**
 * Created by exper on 03.06.2017.
 */


    import com.bitirme.taksishare.mvc.models.Notification;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

    public class NotificationMapper implements ResultSetMapper<Notification> {
        public Notification map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            Notification notification = new Notification();
            notification.setId(r.getInt("id"));
            notification.setReceiverId(r.getInt("receiverId"));
            notification.setSenderId(r.getInt("senderId"));
            notification.setTaxiJourneyId(r.getInt("taxiJourneyId"));
            notification.setMessage(r.getString("message"));
            notification.setDate(r.getString("date"));

            return notification;
        }
    }

