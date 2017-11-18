package com.bitirme.taksishare.factories;

import com.bitirme.taksishare.db.ContentDao;
import com.bitirme.taksishare.db.TaxiJourneyDao;
import com.bitirme.taksishare.db.TaxiJourney_UserDao;
import com.bitirme.taksishare.db.UsersDao;
import com.bitirme.taksishare.db.NotificationDao;

import com.bitirme.taksishare.mvc.controller.TaxiJourney_UserController;
import com.bitirme.taksishare.mvc.models.Notification;
import com.bitirme.taksishare.mvc.models.TaxiJourney;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by YunusS on 3/27/2016.
 */

@Configuration
public class DbiDaoFactory {

    @Autowired
    private DBI dbi;

    @Bean
    public ContentDao getContentDao() {
        return dbi.onDemand(ContentDao.class);
    }

    @Bean
    public UsersDao getUsersDao() { return dbi.onDemand(UsersDao.class); }

    @Bean
    public TaxiJourneyDao getTaxiJourneyDao() { return dbi.onDemand(TaxiJourneyDao.class); }

    @Bean
    public TaxiJourney_UserDao getTaxiJourney_UserDao() { return dbi.onDemand(TaxiJourney_UserDao.class); }

    @Bean
    public NotificationDao getNotificationDao() { return dbi.onDemand(NotificationDao.class); }
}
