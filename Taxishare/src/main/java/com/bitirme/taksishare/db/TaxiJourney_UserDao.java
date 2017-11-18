package com.bitirme.taksishare.db;


import com.bitirme.taksishare.mvc.models.mappers.TaxiJourney_UserMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import com.bitirme.taksishare.mvc.models.TaxiJourney_User;

import java.util.List;

/**
 * Created by exper on 05.05.2017.
 */
public abstract class TaxiJourney_UserDao {



    @SqlUpdate("INSERT INTO taxiJourney_user (taxiJourneyId , userId) VALUES ( :taxiJourneyId ,:userId )")
    @GetGeneratedKeys
    public abstract Integer joinTaxiJourney(@Bind("taxiJourneyId") Integer taxiJourneyId , @Bind("userId") Integer userId );

    @SqlQuery("DELETE FROM taxiJourney_user WHERE taxiJourneyId = :taxiJourneyId and userId = :userId")
    public abstract Integer leaveTaxiJourney(@Bind("taxiJourneyId") Integer taxiJourneyId ,@Bind("userId") Integer userId);

    @SqlQuery("SELECT * FROM taxiJourney_user WHERE taxiJourney_user.taxiJourneyId = :taxiJourneyId  ")
    @Mapper(TaxiJourney_UserMapper.class)
    public abstract List<TaxiJourney_User> getUsersInTaxiJourney(@Bind("taxiJourneyId") Integer taxiJourneyId);

    @SqlQuery("SELECT * FROM taxiJourney_user WHERE taxiJourney_user.userId = :userId  ")
    @Mapper(TaxiJourney_UserMapper.class)
    public abstract List<TaxiJourney_User> getJoinedTaxiJourneys(@Bind("userId") Integer userId);

    @SqlQuery("SELECT * FROM taxiJourney_user WHERE taxiJourney_user.taxiJourneyId = :taxiJourneyId and  taxiJourney_user.userId = :userId ")
    @Mapper(TaxiJourney_UserMapper.class)
    public abstract List<TaxiJourney_User> checkTaxiJourneyIsJoined(@Bind("taxiJourneyId") Integer taxiJourneyId,@Bind("userId") Integer userId);
}
