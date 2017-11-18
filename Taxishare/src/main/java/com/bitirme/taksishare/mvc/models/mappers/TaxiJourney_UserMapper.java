package com.bitirme.taksishare.mvc.models.mappers;

/**
 * Created by exper on 05.05.2017.
 */
import com.bitirme.taksishare.mvc.models.TaxiJourney_User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxiJourney_UserMapper implements ResultSetMapper<TaxiJourney_User> {
    public TaxiJourney_User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        TaxiJourney_User taxiJourney_User = new TaxiJourney_User();
        taxiJourney_User.setTaxiJourneyId(r.getInt("TaxiJourneyId"));
        taxiJourney_User.setUserId(r.getInt("UserId"));


        return taxiJourney_User;
    }
}