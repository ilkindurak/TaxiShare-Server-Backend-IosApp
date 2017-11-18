package com.bitirme.taksishare.services;

import com.bitirme.taksishare.db.NotificationDao;
import com.bitirme.taksishare.db.UsersDao;
import com.bitirme.taksishare.db.TaxiJourneyDao;
import com.bitirme.taksishare.db.TaxiJourney_UserDao;
import com.bitirme.taksishare.mvc.models.*;
import com.bitirme.taksishare.services.NotificationService;
import com.bitirme.taksishare.spring.ActiveUserId;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TaxiJourney_UserService {

    @Autowired
    TaxiJourneyDao taxiJourneyDao;

    @Autowired
    TaxiJourney_UserDao taxiJourney_UserDao;

    @Autowired
    UsersDao userDao;

    @Autowired
    NotificationService notificationService;



    public ActionResult joinTaxiJourney (Integer taxiJourneyId,Integer userId ) {
         Integer receiverId = taxiJourneyDao.getUserIdInTaxiJourney(taxiJourneyId);

        if ( taxiJourney_UserDao.getUsersInTaxiJourney(taxiJourneyId).size() < 4 )  {

            notificationService.createNotification(receiverId,userId,taxiJourneyId);

            taxiJourney_UserDao.joinTaxiJourney(taxiJourneyId , userId );
            return new ActionResult(true, "Taksi yolculuğuna başarıyla katıldınız.");


        }else {

            return new ActionResult(false, "Bu taksi yolculuğu tamamen dolmuş . Lütfen başka bir yolculuk deneyin  ");

        }

    }

    public ActionResult checkTaxiJourneyIsJoined (Integer taxiJourneyId,Integer userId ) {




        if ( taxiJourney_UserDao.checkTaxiJourneyIsJoined(taxiJourneyId,userId)== null &&  userDao.getUserById(userId).getId()==taxiJourneyDao.getUserIdInTaxiJourney(taxiJourneyId))  {


            return new ActionResult(true, "Bu taksi yolculuğu katılınmış değiş");

        }else {

            return new ActionResult(false, "Bu taksi yolculuğu önceden zaten katılınmış bir yolculuk  ");

        }

    }

    public ActionResult leaveTaxiJourney (Integer taxiJourneyId,Integer userId ) {

        if ( taxiJourney_UserDao.leaveTaxiJourney(taxiJourneyId,userId) > 0 )  {


            return new ActionResult(true, "Taksi yolculuğundan başarıyla ayrıldınız.");

        }else {

            return new ActionResult(false, "Taksi yolculuğundan ayrılamadınız.  ");

        }

    }


}
