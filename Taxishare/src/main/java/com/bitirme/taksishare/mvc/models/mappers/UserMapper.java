package com.bitirme.taksishare.mvc.models.mappers;

import com.bitirme.taksishare.mvc.models.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by YunusS on 3/27/2016.
 */
public class UserMapper implements ResultSetMapper<User> {
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setId(r.getInt("id"));
        user.setName(r.getString("name"));
        user.setSurname(r.getString("surname"));
        user.setEmail(r.getString("email"));
        user.setPassword(r.getString("password"));
        user.setPhone(r.getString("phone"));
        user.setGender(r.getInt("gender"));
        user.setBirthdate(r.getString("birthdate"));
        user.setImage(r.getString("image"));
        user.setFbId(r.getString("fbId"));
        user.setFbAccessToken(r.getString("fbAccessToken"));
        return user;
    }
}