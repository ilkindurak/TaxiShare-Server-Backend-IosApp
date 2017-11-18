package com.bitirme.taksishare.mvc.models.mappers;

/**
 * Created by exper on 24.04.2017.
 */

import com.bitirme.taksishare.mvc.models.TaxiJourney;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxiJourneyMapper implements ResultSetMapper<TaxiJourney> {
    public TaxiJourney map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        TaxiJourney taxiJourney = new TaxiJourney();
        taxiJourney.setId(r.getInt("id"));
        taxiJourney.setFromWhere(r.getString("fromWhere"));
        taxiJourney.setFromWhereE(r.getDouble("fromWhereE"));
        taxiJourney.setFromWhereB(r.getDouble("fromWhereB"));
        taxiJourney.setDestination(r.getString("destination"));
        taxiJourney.setDestinationE(r.getDouble("destinationE"));
        taxiJourney.setDestinationB(r.getDouble("destinationB"));
        taxiJourney.setDate(r.getString("date"));

        return taxiJourney;
    }
}
