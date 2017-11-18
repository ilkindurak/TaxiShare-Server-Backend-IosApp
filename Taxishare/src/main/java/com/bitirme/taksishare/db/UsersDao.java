package com.bitirme.taksishare.db;

/**
 * Created by exper on 21.04.2017.
 */

import com.bitirme.taksishare.mvc.models.User;
import com.bitirme.taksishare.mvc.models.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by fikricanca on 18.12.2016.
 */
public abstract class UsersDao {

    @SqlUpdate("INSERT INTO users (name, surname, email, password,  phone, gender, birthdate ,image, fbId, fbAccessToken, token) VALUES (:user.name, :user.surname, :user.email, :user.password,  :user.phone, :user.gender,:user.birthdate,:user.image, :user.fbId, :user.fbAccessToken, :token)")
    @GetGeneratedKeys
    public abstract Integer createUser(@BindBean("user") User user, @Bind("token") String token);

    @SqlUpdate("UPDATE users SET name = COALESCE(:user.name, name), " +
            "surname = COALESCE(:user.surname, surname), " +
            "email = COALESCE(:user.email, email), " +
            "password = COALESCE(:user.password, password), " +
            "phone = COALESCE(:user.phone, phone), " +
            "gender = COALESCE(:user.gender, gender), " +
            "birthdate = COALESCE(:user.birthdate, birthdate), " +
            "image = COALESCE(:user.image, image), " +
            "fbId = COALESCE(:user.fbId, fbId), " +
            "fbAccessToken = COALESCE(:user.fbAccessToken, fbAccessToken) " +
            "WHERE id = :user.id")
    public abstract Integer updateUser(@BindBean("user") User user);

    @SqlQuery("SELECT * FROM users WHERE email=:email")
    @Mapper(UserMapper.class)
    public abstract User getUserByEmail(@Bind("email") String email);

    @SqlQuery("SELECT * FROM users")
    @Mapper(UserMapper.class)
    public abstract List<User> getAllUsers();

    @SqlUpdate("DELETE FROM users WHERE id=:id")
    public abstract void deleteUser(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM users WHERE id=:id")
    @Mapper(UserMapper.class)
    public abstract User getUserById(@Bind("id") Integer id);

    @SqlQuery("SELECT 1 FROM users WHERE id=:id AND password=:password")
    @Mapper(UserMapper.class)
    public abstract List<User> getUserWithIdAndPassword(@Bind("id") Integer userId, @Bind("password") String password);

    @SqlUpdate("UPDATE users SET password=:newPassword WHERE id=:id")
    public abstract void updateUserPassword(@Bind("id") Integer userId, @Bind("newPassword") String newPassword);

    @SqlQuery("SELECT * FROM users WHERE token=:token")
    @Mapper(UserMapper.class)
    public abstract User getUserByToken(@Bind("token") String token);

    @SqlUpdate("UPDATE users SET token = COALESCE(:token, token) WHERE id=:userId")
    public abstract Integer updateTokenByUserId(@Bind("userId") Integer userId, @Bind("token") String token);


}

