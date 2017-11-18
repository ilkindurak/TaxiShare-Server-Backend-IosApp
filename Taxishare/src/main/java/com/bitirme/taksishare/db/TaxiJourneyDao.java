package com.bitirme.taksishare.db;

/**
 * Created by exper on 24.04.2017.
 */

import com.bitirme.taksishare.mvc.models.TaxiJourney;
import com.bitirme.taksishare.mvc.models.mappers.TaxiJourneyMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public abstract  class TaxiJourneyDao {
    @SqlUpdate("INSERT INTO taxiJourneys (userId , fromWhere , fromWhereE ,fromWhereB,destination,  destinationE ,destinationB , date) VALUES (:userId ,:taxiJourney.fromWhere, :taxiJourney.fromWhereE, :taxiJourney.fromWhereB, :taxiJourney.destination,:taxiJourney.destinationE,:taxiJourney.destinationB, :taxiJourney.date)")
    @GetGeneratedKeys
    public abstract Integer createTaxiJourney(@Bind("userId") Integer userId,@BindBean("taxiJourney") TaxiJourney taxiJourney);

    @SqlUpdate("UPDATE taxiJourneys SET " +
            "fromWhere = COALESCE(:taxiJourney.fromWhere, fromWhere), " +
            "fromWhereE = COALESCE(:taxiJourney.fromWhereE, fromWhereE), " +
            "fromWhereB = COALESCE(:taxiJourney.fromWhereB, fromWhereB), " +
            "destination = COALESCE(:taxiJourney.destination, destination), " +
            "destinationE = COALESCE(:taxiJourney.destinationE, destinationE), " +
            "destinationB = COALESCE(:taxiJourney.destinationB, destinationB), " +
            "date = COALESCE(:taxiJourney.date, date), " +
            "WHERE id = :taxiJourney.id")
    public abstract Integer updateTaxiJourney(@BindBean("taxiJourney") TaxiJourney taxiJourney);

    @SqlQuery("SELECT * FROM taxiJourneys WHERE date >= :date and fromWhere = :fromWhere and destination = :destination ORDER BY date ASC")
    @Mapper(TaxiJourneyMapper.class)
    public abstract List<TaxiJourney> getTaxiJourneysBySearch(@Bind("date") String date,@Bind("fromWhere") String fromWhere,@Bind("destination") String destination);


    @SqlQuery("SELECT * FROM taxiJourneys WHERE taxiJourneys.id = :taxiJourneyId")
    @Mapper(TaxiJourneyMapper.class)
    public abstract TaxiJourney getTaxiJourneyById(@Bind("taxiJourneyId") Integer taxiJourneyId);

    @SqlQuery("SELECT userId FROM taxiJourneys WHERE taxiJourneys.id = :taxiJourneyId")
    public abstract Integer  getUserIdInTaxiJourney(@Bind("taxiJourneyId") Integer taxiJourneyId);

    @SqlQuery("SELECT * FROM taxiJourneys WHERE userId = :userId")
    @Mapper(TaxiJourneyMapper.class)
    public abstract List<TaxiJourney> getTaxiJourneysByUserId(@Bind("userId") Integer userId);

    @SqlQuery("SELECT * FROM taxiJourneys WHERE userId = :userId and date >= :date")
    @Mapper(TaxiJourneyMapper.class)
    public abstract List<TaxiJourney> getActiveTaxiJourneysByUserId(@Bind("userId") Integer userId ,@Bind("date") String date);

    @SqlQuery("SELECT * FROM taxiJourneys WHERE date >= :date")
    @Mapper(TaxiJourneyMapper.class)
    public abstract List<TaxiJourney> getTaxiJourneysByDate(@Bind("date") String date);

    @SqlUpdate("DELETE FROM taxiJourneys WHERE id=:taxiJourneyId")
    public abstract Integer deleteTaxiJourney(@Bind("taxiJourneyId") Integer taxiJourneyId);







}
